package com.vicutu.bw.engine.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vicutu.bw.engine.AbstractEngine;
import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.utils.HtmlUtils;
import com.vicutu.bw.utils.HttpUtils;
import com.vicutu.bw.utils.URICollectionFilter;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.SearchStatus;

@Component
public class CastedEuropeEngine extends AbstractEngine implements Engine {

	private static final String ACCESS_DETAIL_NAME = "CastedEurope";

	@Override
	@Autowired
	@Qualifier("defaultHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
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

			this.login(httpClient, accessDetail);

			String linkUrl = accessDetail.getSearchUrl();
			Set<String> finishedPages = new HashSet<String>();
			this.searchPage(linkUrl, finishedPages, accessDetail);
			logger.info("search finished...");
		} catch (Exception e) {
			logger.error("occur error when searching", e);
		} finally {
			this.fireSearchEndEvent();
		}
	}

	private void searchPage(String pageUrl, Set<String> finishedPages, AccessDetail accessDetail) throws Exception {
		logger.info("searching page : {}", pageUrl);
		finishedPages.add(pageUrl);
		String htmlStr = HttpUtils.downloadHtml(httpClient, pageUrl);
		List<String> hrefs = HtmlUtils.selectAllHREF(htmlStr, accessDetail.getBaseUrl());

		List<String> albumUris = URICollectionFilter.valueOf(hrefs).removeDuplicate().selectContains("id=").list();
		for (String albumUri : albumUris) {
			this.searchAlbum(albumUri, accessDetail);
		}
		List<String> pageUris = URICollectionFilter.valueOf(hrefs).removeDuplicate().selectContains("page=").list();
		for (String pageUri : pageUris) {
			if (!finishedPages.contains(pageUri)) {
				this.searchPage(pageUri, finishedPages, accessDetail);
			}
		}
	}

	private void searchAlbum(String albumUri, AccessDetail accessDetail) throws Exception {
		logger.info("searching album : {}", albumUri);
		File folder = new File(accessDetail.getSavePath() + StringUtils.substringAfterLast(albumUri, "id=") + "/");
		if (!folder.exists()) {
			folder.mkdirs();
			logger.info("make dir {}", folder.getAbsoluteFile());
		}

		String htmlStr = HttpUtils.downloadHtml(httpClient, albumUri);
		List<String> hrefs = HtmlUtils.selectAllHREF(htmlStr, accessDetail.getBaseUrl());
		List<String> imageUris = URICollectionFilter.valueOf(hrefs).removeDuplicate().selectContains("getImage.php")
				.list();
		int i = 0;
		for (String imageUri : imageUris) {
			String fileName = String.format("%03d", ++i) + ".jpg";
			File downloadFile = new File(folder, fileName);
			if (downloadFile.exists() && !accessDetail.isReplaceExist()) {
				logger.info("ignore exist : {}", imageUri);
			} else {
				SearchStatus searchStatus = new SearchStatus();
				searchStatus.setLastSearchUrl(albumUri);
				fireDownloadEvent(accessDetail, searchStatus, fileName, downloadFile.getAbsolutePath(), imageUri);
			}
		}
	}

	@Override
	protected String getAccessDetailName() {
		return ACCESS_DETAIL_NAME;
	}

	private void login(HttpClient httpClient, AccessDetail accessDetail) throws Exception {
		String hidden = this.touchVerificationCode(accessDetail);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.err.println("please input the Verification Code\n");
		String word = br.readLine();
		HttpPost httpost = new HttpPost(accessDetail.getLoginUrl());
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", accessDetail.getLoginUsername());
		parameters.put("password", accessDetail.getLoginPassword());
		parameters.put("hidden", hidden);
		parameters.put("word", word);
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
			logger.info("login successful...");
		} else {
			logger.info("login failed...");
		}
	}

	private String touchVerificationCode(AccessDetail accessDetail) throws Exception {
		String loginHtml = HttpUtils.downloadHtml(httpClient, accessDetail.getLoginUrl());

		List<String> images = HtmlUtils.selectAllImage(loginHtml, accessDetail.getBaseUrl());
		String verification = URICollectionFilter.valueOf(images).removeDuplicate().selectContains("captcha.php")
				.list().get(0);

		OutputStream os = FileUtils.openOutputStream(new File(accessDetail.getSavePath() + "verification.bmp"));
		HttpUtils.download(httpClient, verification, os);
		IOUtils.closeQuietly(os);

		List<String> values = HtmlUtils.selectAsString(loginHtml, null, "input[type=hidden]", "value");
		if (!values.isEmpty()) {
			return values.get(0);
		} else {
			return null;
		}
	}
}
