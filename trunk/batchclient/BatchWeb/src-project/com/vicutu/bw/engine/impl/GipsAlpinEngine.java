package com.vicutu.bw.engine.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vicutu.bw.engine.AbstractEngine;
import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.utils.HtmlUtils;
import com.vicutu.bw.utils.HttpUtils;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.SearchStatus;
import com.vicutu.commons.exception.BaseRuntimeException;

@Component
public class GipsAlpinEngine extends AbstractEngine implements Engine {

	private static final String BASE_URL = "http://www.gips-alpin.com/src/en/";

	private static final String IMAGE_ROOT_URL = "http://www.gips-alpin.com/shoots/pix/";

	private static final String ACCESS_DETAIL_NAME = "GipsAlpin";

	@Override
	@Autowired
	@Qualifier("gipsAlpinHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	protected String getAccessDetailName() {
		return ACCESS_DETAIL_NAME;
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void search() {
		try {
			AccessDetail accessDetail = queryAccessDetail();
			if (!accessDetail.isAvailble()) {
				logger.info("Engine [{}] is not avaible now", ACCESS_DETAIL_NAME);
				return;
			}
			this.login(httpClient, accessDetail);

			String linkUrl = accessDetail.getSearchUrl();
			String htmlStr = HttpUtils.downloadHtml(httpClient, linkUrl);
			List<String> firstList = HtmlUtils.selectAllHREF(htmlStr);
			SearchStatus searchStatus = querySearchStatus();
			String lastSearchUrl = searchStatus.getLastSearchUrl();

			if (lastSearchUrl != null) {
				int index = firstList.indexOf(lastSearchUrl);
				if (index >= 0) {
					firstList = firstList.subList(0, index + 1);
				}
			}
			for (String firstUrl : firstList) {
				searchStatus.setLastSearchUrl(firstUrl);
				fireUpdateSearchStatusEvent(searchStatus);

				Map<String, String> parameters = HtmlUtils.getParametersFromUrl(firstUrl);
				String firstUrl0 = BASE_URL + firstUrl;
				String secondHtmlStr = HttpUtils.downloadHtml(httpClient, firstUrl0);
				this.parseSecondPage(parameters.get("aktJahr"), parameters.get("txtMonat"), secondHtmlStr,
						accessDetail, searchStatus);
				logger.info("search finished...");
			}
		} catch (Exception e) {
			logger.error("occur error when searching", e);
		}
	}

	private void parseSecondPage(String year, String month, String html, AccessDetail accessDetail,
			SearchStatus searchStatus) throws Exception {
		Elements elements = HtmlUtils.seletctAsElement(html, BASE_URL, "a[href]");
		for (Element element : elements) {
			String linkUrl0 = element.attr("abs:href");
			String linkName = element.text();
			String htmlStr = HttpUtils.downloadHtml(httpClient, linkUrl0);
			this.parseImagePage(year, month, linkName, htmlStr, accessDetail, searchStatus);
		}
	}

	private void parseImagePage(String year, String month, String label, String html, AccessDetail accessDetail,
			SearchStatus searchStatus) {
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		Matcher matcher = pattern.matcher(label);
		String label0 = matcher.replaceAll("").trim();
		String folderName = accessDetail.getSavePath() + year + "/" + month + "/" + label0;
		File folder = new File(folderName);
		if (!folder.exists() && folder.mkdirs()) {
			logger.info("create new folder : {}", folder.getAbsolutePath());
		}
		List<String> imageUrlList = HtmlUtils.selectAllImage(html);
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

	private void login(HttpClient httpClient, AccessDetail accessDetail) throws Exception {
		HttpPost httpost = new HttpPost(accessDetail.getLoginUrl());
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("flduser", accessDetail.getLoginUsername());
		parameters.put("fldpwd", accessDetail.getLoginPassword());
		Boolean loginResult = HttpUtils.executeLogin(httpClient, httpost, parameters, new ResponseHandler<Boolean>() {
			@Override
			public Boolean handleResponse(HttpResponse response) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					try {
						String html = EntityUtils.toString(response.getEntity());
						if (html.length() > 0) {
							// TODO
							return Boolean.TRUE;
						} else {
							return Boolean.FALSE;
						}
					} catch (Exception e) {
						return Boolean.FALSE;
					}
				} else {
					return Boolean.FALSE;
				}
			}
		});
		if (loginResult.booleanValue()) {
			HttpGet httpget = new HttpGet(accessDetail.getLoginRefreshUrl());
			HttpResponse response = httpClient.execute(httpget);
			response.getEntity().consumeContent();
		} else {
			throw new BaseRuntimeException("log in failed");
		}
	}
}
