package com.vicutu.bw.engine.impl;

import java.io.File;
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
import com.vicutu.bw.engine.DownloadItem;
import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.event.AddDownloadItemEvent;
import com.vicutu.bw.event.UpdateDownloadDetailEvent;
import com.vicutu.bw.event.UpdateSearchStatusEvent;
import com.vicutu.bw.http.utils.HtmlUtils;
import com.vicutu.bw.http.utils.HttpUtils;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;
import com.vicutu.bw.vo.SearchStatus;
import com.vicutu.commons.lang.StringUtils;

@Component
public class BostonBigPictureEngine extends AbstractEngine implements Engine {

	private static final String ACCESS_DETAIL_NAME = "BostonBigPiture";

	@Override
	@Autowired
	@Qualifier("bostonBigPictureHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void search() {
		try {
			AccessDetail accessDetail = accessDetailService.findAccessDetailByName(ACCESS_DETAIL_NAME);
			if (!accessDetail.isAvailble()) {
				logger.info("Engine [{}] is not avaible now", ACCESS_DETAIL_NAME);
				return;
			}
			String startUrl = accessDetail.getSearchUrl();
			String baseUrl = accessDetail.getBaseUrl();
			SearchStatus searchStatus = searchStatusService.findSearchStatusByName(ACCESS_DETAIL_NAME);
			if (searchStatus == null) {
				searchStatus = new SearchStatus();
				searchStatus.setLastSearchUrl(startUrl);
				searchStatus.setAccessName(ACCESS_DETAIL_NAME);
				applicationContext.publishEvent(new UpdateSearchStatusEvent(this, searchStatus));
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
				applicationContext.publishEvent(new UpdateSearchStatusEvent(this, searchStatus));
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
					List<String> imageUrls = HtmlUtils.selectAllJPG((HttpUtils.downloadHtml(httpClient, href)));
					for (String imageUrl : imageUrls) {
						if (!imageUrl.contains("glogo.jpg") && !imageUrl.contains("bp_header_e.jpg")) {
							String fileName = StringUtils.substringAfterLast(imageUrl, "/");
							File downloadFile = new File(folder, fileName);
							fireDownloadEvent(accessDetail, searchStatus, fileName, downloadFile.getAbsolutePath(),
									imageUrl);
						}
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

	private void fireDownloadEvent(AccessDetail accessDetail, SearchStatus searchStatus, String fileName,
			String realPath, String imageUrl0) {
		DownloadDetail downloadDetail = new DownloadDetail();
		downloadDetail.setRealUrl(imageUrl0);
		downloadDetail.setRealPath(realPath);
		downloadDetail.setFileName(fileName);
		logger.info("DownloadDetail-Url : {}", imageUrl0);
		applicationContext.publishEvent(new UpdateDownloadDetailEvent(this, downloadDetail));
		DownloadItem downloadItem = new DownloadItem(accessDetail, downloadDetail, searchStatus, httpClient);
		applicationContext.publishEvent(new AddDownloadItemEvent(this, downloadItem));
	}
}
