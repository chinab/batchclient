package com.vicutu.download.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlUtils {

	public static List<String> getAllLinkUrl(String html) throws ParserException {
		List<String> urlList = new ArrayList<String>();
		if (html != null) {

			Parser parser = new Parser();
			parser.setInputHTML(html);
			NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
			for (int i = 0; i < nla.size(); i++) {
				Node node = nla.elementAt(i);
				if (node instanceof LinkTag) {
					LinkTag lt = (LinkTag) node;
					String linkUrl0 = lt.getLink();
					urlList.add(linkUrl0);
				}
			}

		}
		return urlList;
	}

	public static ListOrderedMap getAllLinkUrlWithLabel(String html) throws ParserException {
		ListOrderedMap listOrderedMap = new ListOrderedMap();
		if (html != null) {

			Parser parser = new Parser();
			parser.setInputHTML(html);
			NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
			for (int i = 0; i < nla.size(); i++) {
				Node node = nla.elementAt(i);
				if (node instanceof LinkTag) {
					LinkTag lt = (LinkTag) node;
					String linkUrl0 = lt.getLink();
					String linkName = lt.getLinkText();
					listOrderedMap.put(i, linkUrl0, linkName);
				}
			}
		}
		return listOrderedMap;
	}

	public static List<String> getAllImageUrl(String html) throws ParserException {
		List<String> urlList = new ArrayList<String>();
		if (html != null) {

			Parser parser = new Parser();
			parser.setInputHTML(html);
			NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("img"));
			for (int i = 0; i < nla.size(); i++) {
				Node node = nla.elementAt(i);
				if (node instanceof ImageTag) {
					ImageTag lt = (ImageTag) node;
					String linkUrl0 = lt.getImageURL();
					urlList.add(linkUrl0);
				}
			}

		}
		return urlList;
	}

	public static Map<String, String> getParametersFromUrl(String url) {
		Map<String, String> parameters = new HashMap<String, String>();
		if (url != null) {

			String url0 = null;
			if (url.indexOf('?') > 0) {
				url0 = StringUtils.substringAfterLast(url, "?");
			} else {
				url0 = url;
			}
			StringTokenizer st = new StringTokenizer(url0, "&");
			while (st.hasMoreTokens()) {
				String searchValue = st.nextToken();
				String key = StringUtils.substringBefore(searchValue, "=");
				String value = StringUtils.substringAfter(searchValue, "=");
				parameters.put(key, value);
			}
		}
		return parameters;
	}
}
