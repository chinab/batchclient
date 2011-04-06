package com.vicutu.batchdownload.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtils {

	private HtmlUtils() {
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

	public static List<String> selectAllHREF(String html, String baseUri) {
		return selectAsString(html, baseUri, "a[href]", "abs:href");
	}

	public static List<String> selectAllImage(String html) {
		return selectAsString(html, null, "img[src]", "src");
	}

	public static List<String> selectAllImage(String html, String baseUri) {
		return selectAsString(html, baseUri, "img[src]", "abs:src");
	}

	public static List<String> selectAllJPG(String html) {
		return selectAsString(html, null, "img[src$=.jpg]", "src");
	}

	public static List<String> selectAllJPG(String html, String baseUri) {
		return selectAsString(html, baseUri, "img[src$=.jpg]", "abs:src");
	}

	public static List<String> selectAllGIF(String html) {
		return selectAsString(html, null, "img[src$=.gif]", "src");
	}

	public static List<String> selectAllGIF(String html, String baseUri) {
		return selectAsString(html, baseUri, "img[src$=.gif]", "abs:src");
	}

	public static List<String> selectImageByType(String html, String type) {
		return selectAsString(html, null, "img[src$=." + type.toLowerCase() + "]", "src");
	}

	public static List<String> selectImageByType(String html, String type, String baseUri) {
		return selectAsString(html, baseUri, "img[src$=." + type.toLowerCase() + "]", "abs:src");
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
				parameters.put(StringUtils.substringBefore(searchValue, "="),
						StringUtils.substringAfter(searchValue, "="));
			}
		}
		return Collections.unmodifiableMap(parameters);
	}
}
