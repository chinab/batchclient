package com.vicutu.batchdownload.engine.impl;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.domain.SearchStatus;
import com.vicutu.batchdownload.engine.AbstractEngine;
import com.vicutu.batchdownload.engine.Engine;
import com.vicutu.batchdownload.utils.HtmlUtils;
import com.vicutu.batchdownload.utils.HttpUtils;
import com.vicutu.batchdownload.utils.URIUtils;

@Component
public class OrientalCastGirlsEngine extends AbstractEngine implements Engine {

	private static final String ACCESS_DETAIL_NAME = "OrientalCastGirls";

	private static final String[] SEARCH_TARGET = new String[] { "cast", "bandage" };

	@Override
	@Autowired
	@Qualifier("defaultHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	protected String getAccessDetailName() {
		return ACCESS_DETAIL_NAME;
	}

	@PostConstruct
	public void init() {
		this.setCredentials(queryAccessDetail());
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void search() {
		this.fireSearchBeginEvent();
		try {
			AccessDetail accessDetail = queryAccessDetail();
			if (accessDetail == null || !accessDetail.isAvailble()) {
				logger.info("Engine [{}] is not avaible now", ACCESS_DETAIL_NAME);
				return;
			}

			String baseUrl = accessDetail.getBaseUrl();

			for (int i = 0; i < SEARCH_TARGET.length; i++) {
				String searchUrl = baseUrl + SEARCH_TARGET[i] + ".htm";
				String savePath = accessDetail.getSavePath() + SEARCH_TARGET[i] + "/";
				logger.info("searching target [{}]", searchUrl);
				doSearch(searchUrl, savePath, accessDetail);
			}
			logger.info("search finished...");
		} catch (Exception e) {
			logger.error("occur error when searching", e);
		} finally {
			this.fireSearchEndEvent();
		}
	}

	private void setCredentials(AccessDetail accessDetail) {
		HttpUtils.setCredentials(httpClient, "www.orientalcastgirls.com", 80, accessDetail.getAuthorizationUsername(),
				accessDetail.getAuthorizationPassword());
	}

	private void doSearch(String searchUrl, String savePath, AccessDetail accessDetail) throws Exception {
		String htmlStr = HttpUtils.downloadHtml(httpClient, searchUrl);
		List<String> hrefList = HtmlUtils.selectAllHREF(htmlStr, accessDetail.getBaseUrl());
		Collection<String> albums = URIUtils.removeContains(hrefList, false, "2009", "2010", "archive");
		for (String albumUrl : albums) {
			logger.info("searching album [{}]", albumUrl);
			searchAlbum(albumUrl, savePath, accessDetail);
		}
	}

	private void searchAlbum(String albumUrl, String savePath, AccessDetail accessDetail) throws Exception {
		String htmlStr = HttpUtils.downloadHtml(httpClient, albumUrl);
		List<String> parts = HtmlUtils.selectAllHREF(htmlStr, accessDetail.getBaseUrl());
		for (String part : parts) {
			if (searchStatusService.urlExists(part)) {
				logger.info("part [{}] has been searched...", part);
			} else {
				logger.info("searching part [{}]", part);
				SearchStatus searchStatus = new SearchStatus();
				searchStatus.setAccessName(getAccessDetailName());
				searchStatus.setLastSearchUrl(part);
				fireUpdateSearchStatusEvent(searchStatus);
				searchPart(part, savePath, accessDetail);
			}
		}
	}

	private void searchPart(String partUrl, String savePath, AccessDetail accessDetail) throws Exception {
		
		String imageBaseUrl = partUrl + "/images/";
		String imgae1htmlUrl = partUrl + "/imagepages/image1.html";

		assembleTask(imgae1htmlUrl, imageBaseUrl, savePath, accessDetail);
	}

	private void assembleTask(String imgae1htmlUrl, String imageBaseUrl, String savePath, AccessDetail accessDetail)
			throws Exception {
		String htmlStr = HttpUtils.downloadHtml(httpClient, imgae1htmlUrl);

		Elements elements = HtmlUtils.seletctAsElement(htmlStr, null, "option");
		for (Element element : elements) {
			String optionText = element.text();
			String imageFileName = StringUtils.substringAfter(optionText, " ");
			String imageUrl = StringUtils.replace(imageBaseUrl + imageFileName, " ", "%20");
			String realFileName = StringUtils.replace(imageFileName, " ", "_");
			File realSavePath = new File(savePath, StringUtils.substringBeforeLast(realFileName, "_"));
			if (!realSavePath.exists()) {
				realSavePath.mkdirs();
			}
			
			File imageFile = new File(realSavePath, realFileName);
			if (imageFile.exists() && !accessDetail.isReplaceExist()) {
				logger.info("ignore exist : {}", imageUrl);
			} else {
				fireDownloadEvent(accessDetail, null, realFileName, imageFile.getAbsolutePath(), imageUrl);
			}
		}
	}
}
