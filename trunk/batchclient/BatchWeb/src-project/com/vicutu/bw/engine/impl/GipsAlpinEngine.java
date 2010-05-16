package com.vicutu.bw.engine.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.springframework.scheduling.annotation.Scheduled;

import com.vicutu.bw.engine.AbstractEngine;
import com.vicutu.bw.utils.HtmlUtils;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;
import com.vicutu.bw.vo.SearchStatus;
import com.vicutu.event.Event;

public class GipsAlpinEngine extends AbstractEngine {

	private static final String BASE_URL = "http://www.gips-alpin.com/src/en/";

	private static final String IMAGE_ROOT_URL = "http://www.gips-alpin.com/shoots/pix/";

	@Override
	protected String getAccessDetailName() {
		return "GipsAlpin";
	}

	@Override
	@Scheduled(fixedRate = 60000)
	public void search() {
		AccessDetail accessDetail = accessDetailService.findAccessDetailByName(this.getAccessDetailName());
		SearchStatus searchStatus = searchStatusService.findSearchStatusByName(this.getAccessDetailName());
		DefaultHttpClient httpClient = null;
		String linkUrl = accessDetail.getSearchUrl();
		String lastSearchUrl = searchStatus.getLastSearchUrl();
		try {
			httpClient = (DefaultHttpClient) httpClientService.getHttpClient(accessDetail.getName(), true);
			String htmlStr = downloadService.downloadHtml(httpClient, linkUrl, accessDetail.getAuthorizationUsername(),
					accessDetail.getAuthorizationPassword());
			List<String> firstList = HtmlUtils.getAllLinkUrl(htmlStr);

			if (lastSearchUrl != null) {
				int index = firstList.indexOf(lastSearchUrl);
				if (index >= 0) {
					firstList = firstList.subList(0, index + 1);
				}
			}
			for (String firstUrl : firstList) {
				searchStatus.setLastSearchUrl(firstUrl);
				Event<SearchStatus> event = new Event<SearchStatus>(this.getClass(), EVENT_TYPE_SEARCH_STATUS, searchStatus);
				applicationContext.publishEvent(event);
				
				Map<String, String> parameters = HtmlUtils.getParametersFromUrl(firstUrl);
				String firstUrl0 = BASE_URL + firstUrl;
				String secondHtmlStr = downloadService.downloadHtml(httpClient, firstUrl0, accessDetail
						.getAuthorizationUsername(), accessDetail.getAuthorizationPassword());
				this.parseSecondPage(parameters.get("aktJahr"), parameters.get("txtMonat"), secondHtmlStr, httpClient,
						accessDetail);
			}
		} catch (Exception e) {
			logger.error("occur error when parsing html", e);
		}
	}

	private void parseSecondPage(String year, String month, String html, DefaultHttpClient httpClient,
			AccessDetail accessDetail) throws Exception {
		Parser parser = new Parser();
		parser.setInputHTML(html);
		NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
		for (int i = 0; i < nla.size(); i++) {
			Node node = nla.elementAt(i);
			if (node instanceof LinkTag) {
				LinkTag lt = (LinkTag) node;
				String linkUrl0 = BASE_URL + lt.getLink();
				String linkName = lt.getLinkText();
				String htmlStr = downloadService.downloadHtml(httpClient, linkUrl0, accessDetail
						.getAuthorizationUsername(), accessDetail.getAuthorizationPassword());
				this.parseImagePage(year, month, linkName, htmlStr, accessDetail, httpClient);
			}
		}
	}

	private void parseImagePage(String year, String month, String label, String html, AccessDetail accessDetail,
			DefaultHttpClient httpClient) throws Exception {
		String label0 = StringUtils.remove(label, '\\');
		label0 = StringUtils.remove(label0, '/');
		label0 = StringUtils.remove(label0, ':');
		label0 = StringUtils.remove(label0, '|');
		label0 = StringUtils.remove(label0, '>');
		label0 = StringUtils.remove(label0, '<');
		label0 = StringUtils.remove(label0, '?');
		label0 = StringUtils.remove(label0, '*');
		String folderName = accessDetail.getSavePath() + year + "/" + month + "/" + label0 + "/";
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		List<String> imageUrlList = HtmlUtils.getAllImageUrl(html);
		for (String imageUrl : imageUrlList) {
			String fileName = StringUtils.substringAfterLast(imageUrl, "/");
			String imageUrl0 = IMAGE_ROOT_URL + StringUtils.substringAfterLast(imageUrl, "thumbs/");

			DownloadDetail downloadDetail = new DownloadDetail();
			downloadDetail.setRealUrl(imageUrl0);
			downloadDetail.setRealPath(folderName + fileName);
			downloadDetail.setFileName(fileName);
			logger.info("DownloadDetail-Url : {}", imageUrl0);
			Event<DownloadDetail> event = new Event<DownloadDetail>(this.getClass(), EVENT_TYPE_DOWNLOAD_DETAIL, downloadDetail);
			applicationContext.publishEvent(event);
			this.download(accessDetail, downloadDetail, httpClient);
		}
	}
}
