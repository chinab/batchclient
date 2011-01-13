package com.vicutu.bw.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.commons.test.LoggedSpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "ctx-dao-test.xml" })
@RunWith(LoggedSpringJUnit4ClassRunner.class)
public class SearchStatusDaoTestCase extends AbstractJUnit4SpringContextTests {

	private SearchStatusDao searchStatusDao;

	@Autowired
	public void setSearchStatusDao(SearchStatusDao searchStatusDao) {
		this.searchStatusDao = searchStatusDao;
	}

	@Test
	public void test_urlExists() {
		logger.info(searchStatusDao
				.urlExists("http://www.orientalcastgirls.com/Member/month_1010/Yuzhen%20LLC/yuzhen%20LLC%2007"));
	}
}
