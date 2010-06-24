package com.vicutu.http;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.commons.test.LoggedSpringJUnit4ClassRunner;
import com.vicutu.http.utils.HtmlUtils;
import com.vicutu.http.utils.HttpUtils;

@ContextConfiguration(locations = { "ctx-http-test.xml" })
@RunWith(LoggedSpringJUnit4ClassRunner.class)
public class HttpTestCase extends AbstractJUnit4SpringContextTests {

	private static final String TEST_URL = "http://www.apache.org";

	private HttpClient httpClient;

	@Autowired
	@Qualifier("defaultHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Test
	public void test_html() throws Exception {
		logger.info(HttpUtils.downloadHtml(httpClient, TEST_URL));
	}

	@Test
	public void test_images() throws Exception {
		String html = HttpUtils.downloadHtml(httpClient, TEST_URL);
		List<String> images = HtmlUtils.selectAllImage(html);
		for (String image : images) {
			logger.info(image);
		}
	}

	@Test
	public void test_hrefs() throws Exception {
		String html = HttpUtils.downloadHtml(httpClient, TEST_URL);
		List<String> hrefs = HtmlUtils.selectAllHREF(html);
		for (String href : hrefs) {
			logger.info(href);
		}
	}

	@Test
	public void test_jpgs() throws Exception {
		String html = HttpUtils.downloadHtml(httpClient, TEST_URL);
		List<String> jpgs = HtmlUtils.selectAllJPG(html);
		for (String jpg : jpgs) {
			logger.info(jpg);
		}
	}

	@Test
	public void test_abs() throws Exception {
		String html = HttpUtils.downloadHtml(httpClient, TEST_URL);
		List<String> hrefs = HtmlUtils.selectAsString(html, TEST_URL, "a[href]", "abs:href");
		for (String href : hrefs) {
			logger.info(href);
		}
	}

	@After
	public void clean_up() {
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
			logger.info("http client has been shut down");
		}
	}
}
