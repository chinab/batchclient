package com.vicutu.bw.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.bw.utils.HtmlUtils;
import com.vicutu.bw.utils.HttpUtils;
import com.vicutu.bw.utils.URIFilter;
import com.vicutu.commons.test.LoggedSpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "ctx-engine-test.xml" })
@RunWith(LoggedSpringJUnit4ClassRunner.class)
public class BeautyLegEngineTestCase extends AbstractJUnit4SpringContextTests {

	private HttpClient httpClient;

	@Autowired
	@Qualifier("defaultHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	private String downloadHtml(String uri) throws Exception {
		return HttpUtils.downloadHtml(httpClient, uri);
	}

	private List<String> combinePages(HttpClient httpClient, String rootUri, String currentUri, String pagePropertyName)
			throws Exception {
		logger.info("page = 1");
		List<String> result = new ArrayList<String>();
		List<String> hrefs = HtmlUtils.selectAllHREF(downloadHtml(currentUri), rootUri);
		URIFilter filter = URIFilter.valueOf(hrefs).selectContains(currentUri);
		Collection<String> albumUris = filter.removeContains(pagePropertyName).removeDuplicate().collection();
		result.addAll(albumUris);
		Collection<String> pageUris = filter.selectContains(pagePropertyName).collection();
		if (!pageUris.isEmpty()) {
			int maxPage = this.getMaxPage(pageUris, pagePropertyName);

			for (int i = 2; i <= maxPage; i++) {
				logger.info("page = " + i);
				hrefs = HtmlUtils.selectAllHREF(
						downloadHtml(new StringBuilder(currentUri).append("?").append(pagePropertyName).append("=")
								.append(i).toString()), rootUri);
				result.addAll(URIFilter.valueOf(hrefs).selectContains(currentUri).removeContains(pagePropertyName)
						.removeDuplicate().collection());
			}
		}
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

	@Test
	public void test_combinePages() throws Exception {

		String rootUri = "http://www.beautyleg.cc";
		List<String> albums = combinePages(httpClient, rootUri,
				"http://www.beautyleg.cc/2010/2010-7-9-No-422-Jellyfish-66P", "page");
		String firstPage = albums.get(0);
		Collection<String> images = URIFilter.valueOf(HtmlUtils.selectAllHREF(downloadHtml(firstPage), rootUri))
				.selectContains("albums").collection();
		if (!images.isEmpty()) {
			String firstImage = ((List<String>) images).get(0);
			logger.info(firstImage);
			firstImage = StringUtils.substringBeforeLast(firstImage, "?m=");
			logger.info(firstImage);
			for (int i = 1; i <= albums.size(); i++) {
				logger.info(StringUtils.replace(firstImage, "001", String.format("%03d", Integer.valueOf(i))));
			}
		}
	}

	@Test
	public void test_format() throws Exception {
		for (int i = 1; i <= 98; i++) {
			logger.info(String.format("%03d", Integer.valueOf(i)));
		}
	}
}
