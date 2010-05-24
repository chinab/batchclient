package com.vicutu.bw.engine.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vicutu.bw.engine.AbstractEngine;
import com.vicutu.bw.engine.DownloadItem;
import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.event.AddDownloadItemEvent;
import com.vicutu.bw.event.UpdateDownloadDetailEvent;
import com.vicutu.bw.event.UpdateSearchStatusEvent;
import com.vicutu.bw.http.utils.DownloadUtils;
import com.vicutu.bw.utils.HtmlUtils;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;
import com.vicutu.bw.vo.SearchStatus;
import com.vicutu.commons.exception.BaseRuntimeException;

@Component
public class GipsAlpinEngine extends AbstractEngine implements Engine {

	private static final String BASE_URL = "http://www.gips-alpin.com/src/en/";

	private static final String IMAGE_ROOT_URL = "http://www.gips-alpin.com/shoots/pix/";

	@Override
	protected String getAccessDetailName() {
		return "GipsAlpin";
	}

	@Override
	@Autowired
	@Qualifier("gipsAlpinHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void search() {
		try {
			AccessDetail accessDetail = accessDetailService.findAccessDetailByName(this.getAccessDetailName());
			this.login(httpClient, accessDetail);
			if (!accessDetail.isAvailble()) {
				logger.info("Engine [{}] is not avaible now", this.getAccessDetailName());
				return;
			}

			String linkUrl = accessDetail.getSearchUrl();
			String htmlStr = DownloadUtils.downloadHtml(httpClient, linkUrl);

			List<String> firstList = HtmlUtils.getAllLinkUrl(htmlStr);

			SearchStatus searchStatus = searchStatusService.findSearchStatusByName(this.getAccessDetailName());
			String lastSearchUrl = searchStatus.getLastSearchUrl();

			if (lastSearchUrl != null) {
				int index = firstList.indexOf(lastSearchUrl);
				if (index >= 0) {
					firstList = firstList.subList(0, index + 1);
				}
			}
			for (String firstUrl : firstList) {
				searchStatus.setLastSearchUrl(firstUrl);
				UpdateSearchStatusEvent updateSearchStatusEvent = new UpdateSearchStatusEvent(this, searchStatus);
				applicationContext.publishEvent(updateSearchStatusEvent);

				Map<String, String> parameters = HtmlUtils.getParametersFromUrl(firstUrl);
				String firstUrl0 = BASE_URL + firstUrl;
				String secondHtmlStr = DownloadUtils.downloadHtml(httpClient, firstUrl0);
				this.parseSecondPage(parameters.get("aktJahr"), parameters.get("txtMonat"), secondHtmlStr,
						accessDetail, searchStatus);
			}
		} catch (Exception e) {
			logger.error("occur error when parsing html", e);
		} finally {
			logger.error("search finish...");
		}
	}

	private void parseSecondPage(String year, String month, String html, AccessDetail accessDetail,
			SearchStatus searchStatus) throws Exception {
		Parser parser = new Parser();
		parser.setInputHTML(html);
		NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
		for (int i = 0; i < nla.size(); i++) {
			Node node = nla.elementAt(i);
			if (node instanceof LinkTag) {
				LinkTag lt = (LinkTag) node;
				String linkUrl0 = BASE_URL + lt.getLink();
				String linkName = lt.getLinkText();
				String htmlStr = DownloadUtils.downloadHtml(httpClient, linkUrl0);
				this.parseImagePage(year, month, linkName, htmlStr, accessDetail, searchStatus);
			}
		}
	}

	private void parseImagePage(String year, String month, String label, String html, AccessDetail accessDetail,
			SearchStatus searchStatus) throws Exception {
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		Matcher matcher = pattern.matcher(label);
		String label0 = matcher.replaceAll("").trim();
		String folderName = accessDetail.getSavePath() + year + "/" + month + "/" + label0;
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		List<String> imageUrlList = HtmlUtils.getAllImageUrl(html);
		for (String imageUrl : imageUrlList) {
			String imageUrl0 = IMAGE_ROOT_URL + StringUtils.substringAfterLast(imageUrl, "thumbs/");
			String fileName = StringUtils.substringAfterLast(imageUrl, "/");
			File downloadFile = new File(folder, fileName);
			if (downloadFile.exists() && !accessDetail.isReplaceExist()) {
				logger.info("ignore exist : {}", imageUrl0);
			} else {
				fireDownloadEvent(accessDetail, searchStatus, fileName, downloadFile.getAbsolutePath(), imageUrl0);
			}
		}
	}

	private void fireDownloadEvent(AccessDetail accessDetail, SearchStatus searchStatus, String fileName,
			String realPath, String imageUrl0) {
		DownloadDetail downloadDetail = new DownloadDetail();
		downloadDetail.setRealUrl(imageUrl0);
		downloadDetail.setRealPath(realPath);
		downloadDetail.setFileName(fileName);
		logger.info("DownloadDetail-Url : {}", imageUrl0);
		UpdateDownloadDetailEvent event = new UpdateDownloadDetailEvent(this, downloadDetail);
		applicationContext.publishEvent(event);
		DownloadItem downloadItem = new DownloadItem(accessDetail, downloadDetail, searchStatus, httpClient);
		AddDownloadItemEvent addDownloadItemEvent = new AddDownloadItemEvent(this, downloadItem);
		applicationContext.publishEvent(addDownloadItemEvent);
	}

	private void login(HttpClient httpClient, AccessDetail accessDetail) throws Exception {
		HttpPost httpost = new HttpPost(accessDetail.getLoginUrl());
		HttpGet httpget = new HttpGet(accessDetail.getLoginRefreshUrl());
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("flduser", accessDetail.getLoginUsername()));
		nvps.add(new BasicNameValuePair("fldpwd", accessDetail.getLoginPassword()));

		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		HttpResponse response = httpClient.execute(httpost);

		response.getEntity().consumeContent();

		response = httpClient.execute(httpget);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String html = EntityUtils.toString(response.getEntity());
			if (html.length() > 0) {
				// TODO
			}
		} else {
			throw new BaseRuntimeException("login failed");
		}
	}
}
