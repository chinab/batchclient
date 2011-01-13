package com.vicutu.bw.engine.impl;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vicutu.bw.engine.AbstractEngine;
import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.utils.HtmlUtils;
import com.vicutu.bw.utils.HttpUtils;
import com.vicutu.bw.utils.URICollectionFilter;
import com.vicutu.bw.utils.URIUtils;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.SearchStatus;

@Component
public class CastedMemoriesEngine extends AbstractEngine implements Engine {

	private static final String ACCESS_DETAIL_NAME = "CastedMemories";

	@Override
	@Autowired
	@Qualifier("defaultHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@PostConstruct
	public void init() {
		this.setCredentials(queryAccessDetail());
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void search() {
		fireSearchBeginEvent();
		try {
			AccessDetail accessDetail = queryAccessDetail();
			if (accessDetail == null || !accessDetail.isAvailble()) {
				logger.info("Engine [{}] is not avaible now", ACCESS_DETAIL_NAME);
				return;
			}

			String htmlStr = HttpUtils.downloadHtml(httpClient, accessDetail.getSearchUrl());
			List<String> hrefList = HtmlUtils.selectAllHREF(htmlStr, accessDetail.getBaseUrl());
			Collection<String> categories = URICollectionFilter.valueOf(hrefList).selectContains("members/")
					.removeEndsWith(".mov").removeDuplicate().collection();
			String[] categoryArray = categories.toArray(new String[categories.size()]);
			for (String category : categories) {
				logger.info("searching category [{}]", category);
				String categoryName = StringUtils.substringBeforeLast(StringUtils.substringAfterLast(category, "/"),
						".htm");
				searchCategory(categoryName, category, categoryArray, accessDetail);
			}

			logger.info("search finished...");
		} catch (Exception e) {
			logger.error("occur error when searching", e);
		} finally {
			this.fireSearchEndEvent();
		}
	}

	private void searchCategory(String categoryName, String categoryUrl, String[] categories, AccessDetail accessDetail)
			throws Exception {
		String htmlStr = HttpUtils.downloadHtml(httpClient, categoryUrl);
		List<String> hrefList = HtmlUtils.selectAllHREF(htmlStr, accessDetail.getBaseUrl());
		Collection<String> albums = URICollectionFilter.valueOf(hrefList).selectContains("members/")
				.removeEndsWith("members/index.htm").removeContains(categories).removeDuplicate().collection();
		for (String album : albums) {
			if (accessDetail.isCheckStatus() && searchStatusService.urlExists(album)) {
				logger.info("category [{}] has been searched...", album);
			} else {
				logger.info("searching category [{}]", album);
				SearchStatus searchStatus = new SearchStatus();
				searchStatus.setAccessName(getAccessDetailName());
				searchStatus.setLastSearchUrl(album);
				fireUpdateSearchStatusEvent(searchStatus);
				String albumName = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(album, "/index.htm"),
						"/");
				searchAlbum(categoryName, albumName, album, accessDetail, searchStatus);
			}
		}
	}

	private void searchAlbum(String categoryName, String albumName, String albumUrl, AccessDetail accessDetail,
			SearchStatus searchStatus) throws Exception {
		File folder = new File(accessDetail.getSavePath() + categoryName + "/" + albumName);
		if (!folder.exists() && folder.mkdirs()) {
			logger.info("create new folder : {}", folder.getAbsolutePath());
		}

		String htmlStr1 = HttpUtils.downloadHtml(httpClient, albumUrl);
		List<String> imageUrlList = HtmlUtils.selectAllImage(htmlStr1);
		Collection<String> images = URIUtils.selectStartsWith(imageUrlList, true, "t");
		String imageBaseUrl = StringUtils.substringBeforeLast(albumUrl, "/");
		String htmlStr2 = HttpUtils.downloadHtml(httpClient, imageBaseUrl + "/index2.htm");
		if (!htmlStr2.contains("Not Found")) {
			images.addAll(URIUtils.selectStartsWith(HtmlUtils.selectAllImage(htmlStr2), true, "t"));
		}

		for (String image : images) {
			String fileName = StringUtils.substringAfter(image, "t");
			String imageUrl0 = imageBaseUrl + "/" + fileName;
			if (StringUtils.endsWithIgnoreCase(fileName, ".jpg")) {
				File downloadFile = new File(folder, fileName);
				if (downloadFile.exists() && !accessDetail.isReplaceExist()) {
					logger.info("ignore exist : {}", imageUrl0);
				} else {
					fireDownloadEvent(accessDetail, searchStatus, fileName, downloadFile.getAbsolutePath(), imageUrl0);
				}
			} else {
				logger.warn("illegal uri of image file : {}", fileName);
			}
		}
	}

	@Override
	protected String getAccessDetailName() {
		return ACCESS_DETAIL_NAME;
	}

	private void setCredentials(AccessDetail accessDetail) {
		HttpUtils.setCredentials(httpClient, "castedmemories.com", 80, accessDetail.getAuthorizationUsername(),
				accessDetail.getAuthorizationPassword());
	}
}
