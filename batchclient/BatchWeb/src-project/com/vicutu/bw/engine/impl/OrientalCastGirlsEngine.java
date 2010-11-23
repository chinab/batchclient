package com.vicutu.bw.engine.impl;

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

import com.vicutu.bw.engine.AbstractEngine;
import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.utils.HtmlUtils;
import com.vicutu.bw.utils.HttpUtils;
import com.vicutu.bw.utils.URIUtils;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.SearchStatus;

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
	public void init(){
		this.setCredentials(queryAccessDetail());
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void search() {
		this.fireSearchBeginEvent();
		try {
			AccessDetail accessDetail = queryAccessDetail();
			if (!accessDetail.isAvailble()) {
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
			if (lastSearchUrlExists(part)) {
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
		String temp = StringUtils.substringAfterLast(partUrl, "Member/");
		File realSavePath = new File(savePath, StringUtils.replace(StringUtils.substringAfter(temp, "/"), "%20", "_"));
		if (!realSavePath.exists()) {
			realSavePath.mkdirs();
		}
		String imageBaseUrl = partUrl + "/images/";
		String imgae1htmlUrl = partUrl + "/imagepages/image1.html";

		assembleTask(imgae1htmlUrl, imageBaseUrl, realSavePath, accessDetail);
	}

	private void assembleTask(String imgae1htmlUrl, String imageBaseUrl, File realSavePath, AccessDetail accessDetail)
			throws Exception {
		String htmlStr = HttpUtils.downloadHtml(httpClient, imgae1htmlUrl);

		Elements elements = HtmlUtils.seletctAsElement(htmlStr, null, "option");
		for (Element element : elements) {
			String optionText = element.text();
			String imageFileName = StringUtils.substringAfter(optionText, " ");
			String imageUrl = StringUtils.replace(imageBaseUrl + imageFileName, " ", "%20");
			File imageFile = new File(realSavePath, StringUtils.replace(imageFileName, " ", "_"));
			if (imageFile.exists() && !accessDetail.isReplaceExist()) {
				logger.info("ignore exist : {}", imageUrl);
			} else {
				fireDownloadEvent(accessDetail, null, imageFileName, imageFile.getAbsolutePath(), imageUrl);
			}
		}
	}
}
