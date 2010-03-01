package com.vicutu.download.seek.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.vicutu.download.seek.AbstractSeeker;
import com.vicutu.download.task.AtomicTask;
import com.vicutu.download.utils.HtmlUtils;

public class GipsAlpinSeeker extends AbstractSeeker {
	private int taskCount;

	private static final String BASE_URL = "http://www.gips-alpin.com/src/en/";

	private static final String IMAGE_ROOT_URL = "http://www.gips-alpin.com/shoots/pix/";

	private String startUrl;

	private String endUrl;

	public void setEndUrl(String endUrl) {
		this.endUrl = endUrl;
	}

	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	@Override
	public Integer call() throws Exception {
		seek(taskDescriptor.getUrlTemplate());
		done = true;
		logger.info("seek over...task count : {}", Integer.valueOf(taskCount));
		return Integer.valueOf(taskCount);
	}

	private void seek(String linkUrl) {
		if (linkUrl == null) {
			return;
		} else {
			linkUrl = StringUtils.trim(linkUrl);
		}
		logger.info("Current linkUrl : {}", linkUrl);
		try {

			String htmlStr = this.getHtmlString(linkUrl);
			List<String> firstList = HtmlUtils.getAllLinkUrl(htmlStr);
			if (startUrl != null) {
				int index = firstList.indexOf(startUrl);
				if (index >= 0) {
					firstList = firstList.subList(index, firstList.size());
				}
			}
			if (endUrl != null) {
				int index = firstList.indexOf(endUrl);
				if (index >= 0) {
					firstList = firstList.subList(0, index + 1);
				}
			}
			for (String firstUrl : firstList) {
				Map<String, String> parameters = HtmlUtils.getParametersFromUrl(firstUrl);
				String firstUrl0 = BASE_URL + firstUrl;
				String secondHtmlStr = this.getHtmlString(firstUrl0);
				this.parseSecondPage(parameters.get("aktJahr"), parameters.get("txtMonat"), secondHtmlStr);
			}

		} catch (ParserException e) {
			logger.error("occur error when parsing html", e);
		}
	}

	private void parseSecondPage(String year, String month, String html) throws ParserException {
		Parser parser = new Parser();
		parser.setInputHTML(html);
		NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
		for (int i = 0; i < nla.size(); i++) {
			Node node = nla.elementAt(i);
			if (node instanceof LinkTag) {
				LinkTag lt = (LinkTag) node;
				String linkUrl0 = BASE_URL + lt.getLink();
				String linkName = lt.getLinkText();

				this.parseImagePage(year, month, linkName, this.getHtmlString(linkUrl0));
			}
		}
	}

	private void parseImagePage(String year, String month, String label, String html) throws ParserException {
		String label0 = StringUtils.remove(label, '\\');
		label0 = StringUtils.remove(label0, '/');
		label0 = StringUtils.remove(label0, ':');
		label0 = StringUtils.remove(label0, '|');
		label0 = StringUtils.remove(label0, '>');
		label0 = StringUtils.remove(label0, '<');
		label0 = StringUtils.remove(label0, '?');
		label0 = StringUtils.remove(label0, '*');
		String folderName = taskDescriptor.getSavePath() + year + "/" + month + "/" + label0;
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		List<String> imageUrlList = HtmlUtils.getAllImageUrl(html);
		for (String imageUrl : imageUrlList) {
			String fileName = StringUtils.substringAfterLast(imageUrl, "/");
			File imageFile = new File(folder, fileName);
			String imageUrl0 = IMAGE_ROOT_URL + StringUtils.substringAfterLast(imageUrl, "thumbs/");
			if (!imageFile.exists()) {
				AtomicTask atomicTask = new AtomicTask();
				atomicTask.setUrl(imageUrl0);
				atomicTask.setSavePath(imageFile);
				logger.info("AtomicTask-Url : {}", atomicTask.getUrl());
				try {
					taskQueue.put(atomicTask);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				taskCount++;
			} else {
				if (replaceExistFile) {
					logger.info("redownload existing file : {}", imageFile);
					AtomicTask atomicTask = new AtomicTask();
					atomicTask.setUrl(imageUrl0);
					atomicTask.setSavePath(imageFile);
					logger.info("AtomicTask-Url : {}", atomicTask.getUrl());
					try {
						taskQueue.put(atomicTask);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					taskCount++;
				} else {
					logger.info("ignore existing file : {}", imageFile);
				}
			}
		}
	}
}
