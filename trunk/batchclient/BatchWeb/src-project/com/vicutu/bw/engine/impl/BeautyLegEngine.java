package com.vicutu.bw.engine.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vicutu.bw.engine.AbstractEngine;
import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.utils.HtmlUtils;
import com.vicutu.bw.utils.HttpUtils;
import com.vicutu.bw.utils.URIFilter;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.SearchStatus;
import com.vicutu.commons.lang.StringUtils;

@Component
public class BeautyLegEngine extends AbstractEngine implements Engine {

	private static final String ACCESS_DETAIL_NAME = "BeautyLeg";

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
			String startUrl = accessDetail.getSearchUrl();
			String baseUrl = accessDetail.getBaseUrl();
			String savePath = accessDetail.getSavePath();
			SearchStatus searchStatus = querySearchStatus();
			if (searchStatus == null) {
				searchStatus = new SearchStatus();
				searchStatus.setLastSearchUrl(startUrl);
				searchStatus.setAccessName(ACCESS_DETAIL_NAME);
				fireUpdateSearchStatusEvent(searchStatus);
			} else {
				startUrl = searchStatus.getLastSearchUrl();
			}
			List<String> allHrefs = HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, baseUrl), baseUrl);
			List<String> baseHrefs = (List<String>) (new URIFilter(allHrefs).selectContainsPattern("\\d{4}$")
					.removeDuplicate().result());
			Collections.sort(baseHrefs);
			int currentSearchIndex = baseHrefs.indexOf(startUrl);
			if (currentSearchIndex > 0) {
				baseHrefs = baseHrefs.subList(currentSearchIndex, baseHrefs.size());
			}
			for (String baseHref : baseHrefs) {
				searchStatus.setLastSearchUrl(baseHref);
				fireUpdateSearchStatusEvent(searchStatus);
				logger.info("searching root url : {}", baseHref);
				List<String> albums = combinePages(httpClient, baseUrl, baseHref, "page");
				for (String album : albums) {
					logger.info("searching album : {}", album);
					File folder = new File(savePath + StringUtils.substringAfter(album, baseUrl) + "/");
					if (!folder.exists()) {
						folder.mkdirs();
					}
					List<String> imagePages = combinePages(httpClient, baseUrl, album, "page");
					if (!imagePages.isEmpty()) {
						String firstPage = imagePages.get(0);
						Collection<String> images = new URIFilter(HtmlUtils.selectAllHREF(
								HttpUtils.downloadHtml(httpClient, firstPage), baseUrl)).selectContains("albums")
								.result();
						if (!images.isEmpty()) {
							String firstImage = ((List<String>) images).get(0);
							firstImage = StringUtils.substringBeforeLast(firstImage, "?m=");
							for (int i = 1; i <= albums.size(); i++) {
								String imageUrl = StringUtils.replace(firstImage, "001",
										String.format("%03d", Integer.valueOf(i)));
								String fileName = StringUtils.substringAfterLast(imageUrl, "/");
								File downloadFile = new File(folder, fileName);
								fireDownloadEvent(accessDetail, searchStatus, fileName, downloadFile.getAbsolutePath(),
										imageUrl);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("occur error when searching", e);
		}
	}

	private List<String> combinePages(HttpClient httpClient, String rootUri, String currentUri, String pagePropertyName)
			throws Exception {

		List<String> result = new ArrayList<String>();
		logger.debug("searching page : {}", currentUri);
		List<String> hrefs = HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, currentUri), rootUri);
		URIFilter filter = new URIFilter(hrefs).selectContains(currentUri);
		Collection<String> albumUris = filter.removeContains(pagePropertyName).removeDuplicate().result();
		result.addAll(albumUris);
		Collection<String> pageUris = filter.selectContains(pagePropertyName).result();
		if (!pageUris.isEmpty()) {
			int maxPage = this.getMaxPage(pageUris, pagePropertyName);

			for (int i = 2; i <= maxPage; i++) {
				String comingUrl = new StringBuilder(currentUri).append("?").append(pagePropertyName).append("=")
						.append(i).toString();
				logger.debug("searching page : {}", comingUrl);
				hrefs = HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, comingUrl), rootUri);
				result.addAll(new URIFilter(hrefs).selectContains(currentUri).removeContains(pagePropertyName)
						.removeDuplicate().result());
			}
		}
		logger.debug("result count : {}", Integer.valueOf(result.size()));
		return result;
	}

	private int getMaxPage(Collection<String> pageUris, String pagePropertyName) {
		int maxPage = 0;
		for (String pageUri : pageUris) {
			Map<String, String> paras = HtmlUtils.getParametersFromUrl(pageUri);
			int page = Integer.valueOf(paras.get(pagePropertyName)).intValue();
			maxPage = page > maxPage ? page : maxPage;
		}
		return maxPage;
	}
}
