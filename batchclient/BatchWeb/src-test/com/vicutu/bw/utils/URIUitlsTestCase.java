package com.vicutu.bw.utils;

import java.util.Collection;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.commons.test.LoggedSpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "ctx-utils-test.xml" })
@RunWith(LoggedSpringJUnit4ClassRunner.class)
public class URIUitlsTestCase extends AbstractJUnit4SpringContextTests {

	private HttpClient httpClient;

	@Autowired
	@Qualifier("defaultHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	private List<String> getHrefs() throws Exception {
		String url = "http://www.beautyleg.cc/2010";
		String html = HttpUtils.downloadHtml(httpClient, url);
		List<String> hrefs = HtmlUtils.selectAsString(html, url, "a[href]", "abs:href");
		for (String href : hrefs) {
			logger.info(href);
		}
		logger.info("##########################");
		return hrefs;
	}

	@Test
	public void test_removeDuplicate() throws Exception {
		List<String> hrefs = getHrefs();

		Collection<String> c = URIUtils.removeDuplicate(hrefs);
		for (String s : c) {
			logger.info(s);
		}
	}

	@Test
	public void test_removeStartsWith() throws Exception {
		List<String> hrefs = getHrefs();
		Collection<String> c = URIUtils.removeStartsWith(hrefs, false, "www.beautyleg.cc");
		for (String s : c) {
			logger.info(s);
		}
	}

	@Test
	public void test_selectStartsWith() throws Exception {
		List<String> hrefs = getHrefs();
		Collection<String> c = URIUtils.selectStartsWith(hrefs, false, "www.beautyleg.cc");
		for (String s : c) {
			logger.info(s);
		}
	}

	@Test
	public void test_removeEndsWith() throws Exception {
		List<String> hrefs = getHrefs();
		Collection<String> c = URIUtils.removeEndsWith(hrefs, false, "2009");
		for (String s : c) {
			logger.info(s);
		}
	}

	@Test
	public void test_selectEndsWith() throws Exception {
		List<String> hrefs = getHrefs();
		Collection<String> c = URIUtils.selectEndsWith(hrefs, false, "2009");
		for (String s : c) {
			logger.info(s);
		}
	}

	@Test
	public void test_removeContains() throws Exception {
		List<String> hrefs = getHrefs();
		Collection<String> c = URIUtils.removeContains(hrefs, false, "tag");
		for (String s : c) {
			logger.info(s);
		}
	}

	@Test
	public void test_removeByPattern() throws Exception {
		List<String> hrefs = getHrefs();
		Collection<String> c = URIUtils.selectContains(hrefs, false, "tag");
		for (String s : c) {
			logger.info(s);
		}
	}

	@Test
	public void test_selectContainsPattern() throws Exception {
		List<String> hrefs = getHrefs();
		Collection<String> c = URIUtils.selectContainsPattern(hrefs, "\\d{4}$");
		for (String s : c) {
			logger.info(s);
		}
	}

	@Test
	public void test_URIFilter() throws Exception {
		List<String> hrefs = getHrefs();
		for (String s : URIFilter.valueOf(hrefs).removeDuplicate().selectContains("http://www.beautyleg.cc/2010")
				.collection()) {
			logger.info(s);
		}
	}
}
