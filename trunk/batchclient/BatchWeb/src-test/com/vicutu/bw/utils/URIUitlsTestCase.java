package com.vicutu.bw.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.client.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.commons.lang.StringUtils;
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
		for (String s : URICollectionFilter.valueOf(hrefs).removeDuplicate()
				.selectContains("http://www.beautyleg.cc/2010").collection()) {
			logger.info(s);
		}
	}

	@Test
	public void test_combine() throws Exception {
		String url = "http://www.beautyleg.cc/2010/2010-8-10-No-433-Jessie-68P";
		String baseUrl = "http://www.beautyleg.cc";
		List<String> imagePages = combinePages(httpClient, baseUrl, url, "page");

		if (!imagePages.isEmpty()) {
			String firstPage = imagePages.get(0);
			Collection<String> images = URICollectionFilter
					.valueOf(HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, firstPage), baseUrl))
					.selectContains("albums").collection();
			if (!images.isEmpty()) {
				String firstImage = ((List<String>) images).get(0);
				firstImage = StringUtils.substringBeforeLast(firstImage, "?m=");
				for (String imagePage : imagePages) {
					String imageUrl = StringUtils.replaceEach(firstImage, new String[] { "0000", "001" }, new String[] {
							StringUtils.right(imagePage, 4), StringUtils.right(imagePage, 3) });
					StringUtils.substringAfterLast(imageUrl, "/");
				}
			}
		}
	}

	private List<String> combinePages(HttpClient httpClient, String rootUri, String currentUri, String pagePropertyName)
			throws Exception {

		List<String> result = new ArrayList<String>();
		List<String> hrefs = HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, currentUri), rootUri);
		Collection<String> allUris = URICollectionFilter.valueOf(hrefs).selectContains(currentUri).collection();
		Collection<String> albumUris = URICollectionFilter.valueOf(allUris).removeContains(pagePropertyName)
				.removeDuplicate().collection();
		result.addAll(albumUris);
		Collection<String> pageUris = URICollectionFilter.valueOf(allUris).selectContains(pagePropertyName)
				.collection();
		if (!pageUris.isEmpty()) {
			int maxPage = this.getMaxPage(pageUris, pagePropertyName);

			for (int i = 2; i <= maxPage; i++) {
				String comingUrl = new StringBuilder(currentUri).append("?").append(pagePropertyName).append("=")
						.append(i).toString();
				hrefs = HtmlUtils.selectAllHREF(HttpUtils.downloadHtml(httpClient, comingUrl), rootUri);
				result.addAll(URICollectionFilter.valueOf(hrefs).selectContains(currentUri)
						.removeContains(pagePropertyName).removeDuplicate().collection());
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
	public void test_yesOrNo() {
		logger.info(RandomUtils.nextBoolean());
	}
}
