package com.vicutu.download.alone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.vicutu.commons.lang.FileUtils;

public class Deviantart {
	private static final String BASE_URL = "http://excilion.deviantart.com/gallery/#_featured";

	private static final int FROM = 1;

	private static final int TO = 21;

	private static final String SAVE_PATH = "J:/sec/cast/cartoon/";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String linkUrl = null;

		for (int i = FROM; i <= TO; i++) {
			if (i == 1) {
				linkUrl = BASE_URL;
			} else {
				linkUrl = BASE_URL + "--" + i;
			}

			System.out.println("searching url : " + linkUrl);

			String htmlStr = getHtmlStr(httpclient, linkUrl, "iso-8859-1");
			List<String> imageUrls = getImageUrl(htmlStr);
			download(httpclient, imageUrls);
		}

	}

	private static String getHtmlStr(DefaultHttpClient httpclient, String linkUrl, String encoding) throws Exception {
		HttpGet httpget = new HttpGet(linkUrl);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			return EntityUtils.toString(entity, encoding);
		}
		return null;
	}

	private static List<String> getImageUrl(String htmlStr) throws Exception {
		List<String> imageUrls = new ArrayList<String>();
		Parser parser = new Parser();
		parser.setInputHTML(htmlStr);
		NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("img"));
		String imageUrl = null;
		for (int i = 0; i < nla.size(); i++) {
			Node node = nla.elementAt(i);
			if (node instanceof ImageTag) {
				ImageTag it = (ImageTag) node;
				String linkUrl0 = it.getImageURL();
				if (linkUrl0.startsWith("http://th")) {
					imageUrl = StringUtils.replaceOnce(StringUtils.remove(linkUrl0, "150/"), "th", "fc");
					imageUrls.add(imageUrl);
				}
			}
		}
		return imageUrls;
	}

	private static void download(DefaultHttpClient httpclient, List<String> imageUrls) {
		FileOutputStream fos = null;
		HttpGet httpget = null;
		HttpEntity entity = null;
		HttpResponse response = null;
		for (int i = 0; i < imageUrls.size(); i++) {
			String imageUrl = imageUrls.get(i);
			String fileName = StringUtils.substringAfterLast(imageUrl, "/");
			try {
				File file = new File(SAVE_PATH, fileName);
				if (file.exists()) {
					System.out.println("file exist : " + file.getName());
					continue;
				}

				fos = FileUtils.openOutputStream(file);
				httpget = new HttpGet(imageUrl);
				response = httpclient.execute(httpget);
				entity = response.getEntity();
				IOUtils.copy(entity.getContent(), fos);
				System.out.println("download file : " + file.getName());
			} catch (IOException e) {
				if (entity != null) {
					try {
						entity.consumeContent();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} finally {
				IOUtils.closeQuietly(fos);
			}
		}
	}

}
