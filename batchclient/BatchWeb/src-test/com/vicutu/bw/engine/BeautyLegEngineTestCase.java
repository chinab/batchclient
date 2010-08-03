package com.vicutu.bw.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
		URIFilter filter = new URIFilter(hrefs).selectContains(currentUri);
		Collection<String> pageUris = filter.selectContains(pagePropertyName).result();
		int maxPage = this.getMaxPage(pageUris, pagePropertyName);
		Collection<String> albumUris = filter.removeContains(pagePropertyName).result();
		result.addAll(albumUris);
		for (int i = 2; i <= maxPage; i++) {
			logger.info("page = " + i);
			hrefs = HtmlUtils.selectAllHREF(
					downloadHtml(new StringBuilder(currentUri).append("?").append(pagePropertyName).append("=")
							.append(i).toString()), rootUri);
			result.addAll(new URIFilter(hrefs).selectContains(currentUri).removeContains(pagePropertyName).result());
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
		List<String> albums = combinePages(httpClient, "http://www.beautyleg.cc", "http://www.beautyleg.cc/2010",
				"page");
		for (String album : albums) {
			logger.info(album);
		}
	}
}
