package com.vicutu.bw.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.commons.test.LoggedSpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "ctx-dao-test.xml" })
@RunWith(LoggedSpringJUnit4ClassRunner.class)
public class AccessDetailDaoTestCase extends AbstractJUnit4SpringContextTests {

	private AccessDetailDao accessDetailDao;

	@Autowired
	public void setAccessDetailDao(AccessDetailDao accessDetailDao) {
		this.accessDetailDao = accessDetailDao;
	}

	@Test
	public void test_findAll() {
		List<AccessDetail> accessDetailList = accessDetailDao.findAllAccessDetail();
		if (accessDetailList != null) {
			for (AccessDetail accessDetail : accessDetailList) {
				logger.info(accessDetail);
			}
		}
	}

	@Test
	public void test_findByName() {
		AccessDetail accessDetail = accessDetailDao.findAccessDetailByName("GipsAlpin");
		logger.info(accessDetail);
	}
}
