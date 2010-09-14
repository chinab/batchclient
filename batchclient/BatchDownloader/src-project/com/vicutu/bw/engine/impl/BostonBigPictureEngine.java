package com.vicutu.bw.engine.impl;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
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
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.SearchStatus;
import com.vicutu.commons.lang.StringUtils;

@Component
public class BostonBigPictureEngine extends AbstractEngine implements Engine {

	private static final String ACCESS_DETAIL_NAME = "BostonBigPiture";

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
			SearchStatus searchStatus = querySearchStatus();
			if (searchStatus == null) {
				searchStatus = new SearchStatus();
				searchStatus.setLastSearchUrl(startUrl);
				searchStatus.setAccessName(ACCESS_DETAIL_NAME);
				fireUpdateSearchStatusEvent(searchStatus);
			} else {
				startUrl = searchStatus.getLastSearchUrl();
			}
			Date currentTime = new Date(System.currentTimeMillis());
			Date startTime = parseStartTimeFromUrl(currentTime, startUrl, baseUrl);
			Date searchDate = startTime;
			while (searchDate.equals(currentTime) || searchDate.before(currentTime)) {
				String folderName = DateFormatUtils.format(searchDate, "yyyy/MM");
				String searchUrl = baseUrl + folderName;
				searchStatus.setLastSearchUrl(searchUrl);
				fireUpdateSearchStatusEvent(searchStatus);
				List<String> hrefList = HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, searchUrl));
				ListOrderedSet los = new ListOrderedSet();
				for (String tempHref : hrefList) {
					if (tempHref.startsWith(baseUrl)) {
						los.add(tempHref);
					}
				}
				@SuppressWarnings("unchecked")
				Iterator<String> hrefs = los.iterator();
				while (hrefs.hasNext()) {
					String href = hrefs.next();//http://www.boston.com/bigpicture/2008/05/cassini_nears_fouryear_mark.html
					File folder = new File(
							accessDetail.getSavePath()
									+ StringUtils.removeEndIgnoreCase(StringUtils.removeStartIgnoreCase(href, baseUrl),
											".html"));
					if (!folder.exists() && folder.mkdirs()) {
						logger.info("create new folder : {}", folder.getAbsolutePath());
					}
					Collection<String> imageUrls = URICollectionFilter
							.valueOf(HtmlUtils.selectAllJPG((HttpUtils.downloadHtml(httpClient, href))))
							.removeContains("glogo.jpg", "bp_header_e.jpg").collection();
					for (String imageUrl : imageUrls) {
						String fileName = StringUtils.substringAfterLast(imageUrl, "/");
						File downloadFile = new File(folder, fileName);
						fireDownloadEvent(accessDetail, searchStatus, fileName, downloadFile.getAbsolutePath(),
								imageUrl);
					}
				}
				searchDate = DateUtils.addMonths(searchDate, 1);
			}
		} catch (Exception e) {
			logger.error("occur error when searching", e);
		}
	}

	private Date parseStartTimeFromUrl(Date currentTime, String startUrl, String baseUrl) {
		String[] yAndMStr = StringUtils.split(StringUtils.removeStartIgnoreCase(startUrl, baseUrl), "/");
		int year = Integer.valueOf(yAndMStr[0]);
		int month = Integer.valueOf(yAndMStr[1]);
		return DateUtils.setMonths(DateUtils.setYears(currentTime, year), month - 1);
	}
}
