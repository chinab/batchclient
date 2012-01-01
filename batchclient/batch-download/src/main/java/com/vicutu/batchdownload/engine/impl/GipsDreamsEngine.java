package com.vicutu.batchdownload.engine.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.domain.SearchStatus;
import com.vicutu.batchdownload.engine.AbstractEngine;
import com.vicutu.batchdownload.engine.Engine;
import com.vicutu.batchdownload.engine.io.FileHandler;
import com.vicutu.batchdownload.utils.HtmlUtils;
import com.vicutu.batchdownload.utils.HttpUtils;
import com.vicutu.batchdownload.utils.URICollectionFilter;
import com.vicutu.batchdownload.utils.URIUtils;

@Component
public class GipsDreamsEngine extends AbstractEngine implements Engine {

	private static final String ACCESS_DETAIL_NAME = "GipsDreams";

	private static final String[] SEARCH_TARGET = new String[] { "pictures", "movie_clips"};

	@Override
	@Autowired
	@Qualifier("defaultHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	@Autowired
	@Qualifier("simpleFileHandler")
	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
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
				String targetUrl = baseUrl + SEARCH_TARGET[i] + ".php";
				String savePath = accessDetail.getSavePath() + SEARCH_TARGET[i] + "/";
				logger.info("searching target [{}]", targetUrl);
				logger.info("saving path [{}]", savePath);
				accessDetail.setBaseUrl(targetUrl);
				doSearch(targetUrl, savePath, accessDetail);
			}
			logger.info("search finished...");
		} catch (Exception e) {
			logger.error("occur error when searching", e);
		} finally {
			this.fireSearchEndEvent();
		}
	}

	private void setCredentials(AccessDetail accessDetail) {
		HttpUtils.setCredentials(httpClient, "www.gipsdreams.com", 80, accessDetail.getAuthorizationUsername(),
				accessDetail.getAuthorizationPassword());
	}

	private void doSearch(String searchUrl, String savePath, AccessDetail accessDetail) throws Exception {
		String htmlStr = this.downloadHtml(httpClient, searchUrl);
		List<String> catalogPageList = HtmlUtils.selectAllHREF(htmlStr, accessDetail.getBaseUrl());
		Collection<String> catalogPages = URIUtils.selectContains(catalogPageList, false, "?page");
		for (String catalogPage : catalogPages) {
			logger.info("searching catalog page [{}]", catalogPage);
			searchCatalogPage(catalogPage, savePath, accessDetail);
		}
	}

	private void searchCatalogPage(String catalogUrl, String savePath, AccessDetail accessDetail) throws Exception {
		String htmlStr = this.downloadHtml(httpClient, catalogUrl);

		List<String> albumList = HtmlUtils.selectAllHREF(htmlStr, accessDetail.getBaseUrl());
		Collection<String> albums = URIUtils.selectContains(albumList, false, "?gid");

		for (String album : albums) {
			if (searchStatusService.urlExists(album)) {
				logger.info("album [{}] has been searched...", album);
			} else {
				logger.info("searching album [{}]", album);
				SearchStatus searchStatus = new SearchStatus();
				searchStatus.setAccessName(getAccessDetailName());
				searchStatus.setLastSearchUrl(album);
				fireUpdateSearchStatusEvent(searchStatus);
				searchAlbum(album, savePath, accessDetail);
			}
		}
	}

	private void searchAlbum(String albumUrl, String savePath, AccessDetail accessDetail) throws Exception {
		String htmlStr = this.downloadHtml(httpClient, albumUrl);
		List<String> itemPageList = HtmlUtils.selectAllHREF(htmlStr, accessDetail.getBaseUrl());
		Collection<String> itemPages = URIUtils.selectContains(itemPageList, false, "?gid");

		for (String itemPage : itemPages) {
			logger.info("searching item page [{}]", itemPage);
			searchItem(itemPage, savePath, accessDetail);
		}

	}

	private void searchItem(String itemUrl, String savePath, AccessDetail accessDetail) throws Exception {
		String htmlStr = this.downloadHtml(httpClient, itemUrl);

		List<String> itemList = HtmlUtils.selectAllHREF(htmlStr);
		Collection<String> items = URICollectionFilter.valueOf(itemList).removeContains("thumbnails")
				.selectContains("_gallery").collection();
		for (String item : items) {
			assembleTask(item, savePath, accessDetail);
		}
	}

	private void assembleTask(String item, String savePath, AccessDetail accessDetail) throws Exception {

		String folderName = StringUtils.substringBefore(StringUtils.substringAfterLast(item, "_gallery/"), "/");
		String realFileName = StringUtils.substringAfterLast(item, "/");
		String realSavePath = null;
		if (StringUtils.endsWithAny(savePath, new String[] { "/", "\\" })) {
			realSavePath = new StringBuilder().append(savePath).append(folderName).toString();
		} else {
			realSavePath = new StringBuilder().append(savePath).append("/").append(folderName).toString();
		}

		if (fileHandler.exists(realSavePath, realFileName) && !accessDetail.isReplaceExist()) {
			logger.info("ignore exist : {}", item);
		} else {
			fireDownloadEvent(accessDetail, null, StringUtils.replace(item, " ", "%20"), realSavePath, realFileName);
		}

	}

	private String downloadHtml(HttpClient httpClient, String linkUrl) {
		try {
			return HttpUtils.downloadHtml(httpClient, linkUrl);
		} catch (Exception e) {
			logger.error("occur error when searching [" + linkUrl + "]", e);
			return "";
		}
	}
}
