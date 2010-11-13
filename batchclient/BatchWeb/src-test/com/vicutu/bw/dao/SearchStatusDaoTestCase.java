package com.vicutu.bw.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.bw.vo.SearchStatus;
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
	public void test_findByName() {
		SearchStatus searchStatus = searchStatusDao.findSearchStatusByName("GipsAlpin");
		logger.info(searchStatus);
	}

	@Test
	public void test_lastSearchUrlExists() {
		logger.info(searchStatusDao.lastSearchUrlExists("OrientalCastGirls",
				"http://www.orientalcastgirls.com/Member/ModelBased/Chengyu%20HIP/Chenyu%20HIP%2002/"));
	}
}
