package com.vicutu.bw.utils;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.commons.test.LoggedSpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "ctx-utils-test.xml" })
@RunWith(LoggedSpringJUnit4ClassRunner.class)
public class HttpUtilsTestCase extends AbstractJUnit4SpringContextTests {

	@Test
	public void test_select() throws Exception {
		String htmlStr = FileUtils.readFileToString(new File("E:/downloads/new.html"));
		List<String> hrefs = HtmlUtils.selectAllHREF(htmlStr);
		for (String href : hrefs) {
			logger.info(href);
		}
	}
}
