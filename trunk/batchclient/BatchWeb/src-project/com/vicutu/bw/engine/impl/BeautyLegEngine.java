package com.vicutu.bw.engine.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.functors.OrPredicate;
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
import com.vicutu.bw.utils.functors.ContainsPatternPredicate;
import com.vicutu.bw.utils.functors.ContentPredicate;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.SearchStatus;
import com.vicutu.commons.lang.StringUtils;

@Component
public class BeautyLegEngine extends AbstractEngine implements Engine {

	private static final String ACCESS_DETAIL_NAME = "BeautyLeg";

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

	@Scheduled(fixedDelay = 600000)
	public void search() {
		try {
			AccessDetail accessDetail = queryAccessDetail();
			if (accessDetail == null || !accessDetail.isAvailble()) {
				logger.info("Engine [{}] is not avaible now", ACCESS_DETAIL_NAME);
				return;
			}
			String startUrl = accessDetail.getSearchUrl();
			String baseUrl = accessDetail.getBaseUrl();
			String savePath = accessDetail.getSavePath();
			SearchStatus searchStatus = new SearchStatus();
			List<String> allHrefs = HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, baseUrl), baseUrl);
			List<String> baseHrefs = URICollectionFilter
					.valueOf(allHrefs)
					.removeDuplicate()
					.select(new OrPredicate(new ContainsPatternPredicate("\\d{4}$"), new ContentPredicate("new", true)))
					.removeContains("rss").list();
			Collections.sort(baseHrefs);
			int currentSearchIndex = baseHrefs.indexOf(startUrl);
			if (currentSearchIndex > 0) {
				baseHrefs = baseHrefs.subList(currentSearchIndex, baseHrefs.size());
			}
			for (String baseHref : baseHrefs) {
				searchStatus.setLastSearchTime(new Date(System.currentTimeMillis()));
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
						String secondPage = imagePages.get(1);
						Collection<String> firstImages = URICollectionFilter
								.valueOf(
										HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, firstPage), baseUrl))
								.selectContains("albums").collection();
						Collection<String> secondImages = URICollectionFilter
								.valueOf(
										HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, secondPage), baseUrl))
								.selectContains("albums").collection();
						if (!firstImages.isEmpty()) {
							String firstImage = StringUtils.substringBeforeLast(((List<String>) firstImages).get(0),
									"?m=");
							String secondImage = StringUtils.substringBeforeLast(((List<String>) secondImages).get(0),
									"?m=");
							if (StringUtils.contains(firstImage, "0000")) {
								if (StringUtils.contains(secondImage, "000")) {
									//head is the same as others
									for (String imagePage : imagePages) {
										String imageUrl = StringUtils.replace(firstImage, "0000",
												StringUtils.right(imagePage, 4));
										String fileName = StringUtils.substringAfterLast(imageUrl, "/");
										File downloadFile = new File(folder, fileName);
										fireDownloadEvent(accessDetail, searchStatus, fileName,
												downloadFile.getAbsolutePath(), imageUrl);
									}
								} else {
									//head is 0000, others are like 001,002,003......
									//download firstImage first
									String fileName = StringUtils.substringAfterLast(firstImage, "/");
									File downloadFile = new File(folder, fileName);
									fireDownloadEvent(accessDetail, searchStatus, fileName,
											downloadFile.getAbsolutePath(), firstImage);
									//and then download others, use secondImage as the template
									for (int i = 1; i < imagePages.size(); i++) {
										String imagePage = imagePages.get(i);
										String imageUrl = StringUtils.replace(secondImage, "001",
												StringUtils.right(imagePage, 3));
										fileName = StringUtils.substringAfterLast(imageUrl, "/");
										downloadFile = new File(folder, fileName);
										fireDownloadEvent(accessDetail, searchStatus, fileName,
												downloadFile.getAbsolutePath(), imageUrl);
									}
								}
							} else {
								//no head, all images are like 001,002,003
								for (String imagePage : imagePages) {
									String imageUrl = StringUtils.replace(firstImage, "001",
											StringUtils.right(imagePage, 3));
									String fileName = StringUtils.substringAfterLast(imageUrl, "/");
									File downloadFile = new File(folder, fileName);
									fireDownloadEvent(accessDetail, searchStatus, fileName,
											downloadFile.getAbsolutePath(), imageUrl);
								}
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
		logger.info("searching page : {}", currentUri);
		List<String> hrefs = HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, currentUri), rootUri);
		Collection<String> allUris = URICollectionFilter.valueOf(hrefs).selectContains(currentUri).collection();
		Collection<String> albumUris = URICollectionFilter.valueOf(allUris).removeContains(pagePropertyName)
				.removeDuplicate().collection();
		result.addAll(albumUris);
		Collection<String> pageUris = URICollectionFilter.valueOf(allUris).selectContains(pagePropertyName)
				.collection();
		if (!pageUris.isEmpty()) {
			int maxPage = this.getMaxPage(pageUris, pagePropertyName);

			for (int i = 2; i <= maxPage; i++) {
				String comingUrl = new StringBuilder(currentUri).append("?").append(pagePropertyName).append("=")
						.append(i).toString();
				logger.info("searching page : {}", comingUrl);
				hrefs = HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, comingUrl), rootUri);
				result.addAll(URICollectionFilter.valueOf(hrefs).selectContains(currentUri)
						.removeContains(pagePropertyName).removeDuplicate().collection());
			}
		}
		logger.info("result count : {}", Integer.valueOf(result.size()));
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
