package com.vicutu.bw.http.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtils {

	private HtmlUtils() {
	}

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

	public static List<String> selectAsString(String html, String baseUri, String selectPattern, String attr) {
		List<String> list = new ArrayList<String>();
		Elements elements = seletctAsElement(html, baseUri, selectPattern);
		for (Element element : elements) {
			list.add(element.attr(attr));
		}
		return list;
	}

	public static Elements seletctAsElement(String html, String baseUri, String selectPattern) {
		Document doc = baseUri == null ? Jsoup.parse(html) : Jsoup.parse(html, baseUri);
		return doc.select(selectPattern);
	}

	public static List<String> selectAllHREF(String html) {
		return selectAsString(html, null, "a[href]", "href");
	}

	public static List<String> selectAllImage(String html) {
		return selectAsString(html, null, "img[src]", "src");
	}

	public static List<String> selectAllJPG(String html) {
		return selectAsString(html, null, "img[src$=.jpg]", "src");
	}

	public static List<String> selectAllGIF(String html) {
		return selectAsString(html, null, "img[src$=.gif]", "src");
	}

	public static List<String> selectImageByType(String html, String type) {
		return selectAsString(html, null, "img[src$=." + type.toLowerCase() + "]", "src");
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
				parameters.put(StringUtils.substringBefore(searchValue, "="), StringUtils.substringAfter(searchValue,
						"="));
			}
		}
		return parameters;
	}
}
