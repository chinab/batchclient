package com.mindstatus.module;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.mindstatus.module.query.MsEmployeeQuery;
import com.vicutu.commons.test.SpringBaseTestCase;
import com.vicutu.persistence.query.Pagination;

public class MsEmployeeDaoTestCase extends SpringBaseTestCase
{
	public void test_findAll()
	{
		IMsEmployeeDao msEmployeeDao = (IMsEmployeeDao) getBean(IMsEmployeeDao.class);
		List<MsEmployee> msEmployeeList = msEmployeeDao.findAll();
		Iterator<MsEmployee> msEmployeeIt = msEmployeeList.iterator();
		while (msEmployeeIt.hasNext())
		{
			logger.println(msEmployeeIt.next());
		}
	}

	public void test_saveOrUpdate()
	{
		Random random = new Random();
		for (int i = 0; i < 300; i++)
		{
			MsEmployee msEmployee = new MsEmployee();
			msEmployee.setName("z" + i);
			int sex = random.nextInt() % 2;
			if(sex == 0)
			{
				msEmployee.setSex(new Integer(2));
			}else
			{
				msEmployee.setSex(new Integer(1));
			}
			msEmployee.setAge(new Integer(24));
			msEmployee.setCampaign(new Integer(1));
			msEmployee.setCommunity(new Integer(2));
			msEmployee.setEnlighten(new Integer(1));
			msEmployee.setBirthday(new Timestamp(System.currentTimeMillis()));
			IMsEmployeeDao msEmployeeDao = (IMsEmployeeDao) getBean(IMsEmployeeDao.class);
			msEmployeeDao.saveOrUpdate(msEmployee);
		}
	}

	public void test_findPageByCondition()
	{
		MsEmployeeQuery msEmployeeQuery = new MsEmployeeQuery();
		msEmployeeQuery.setName("张");
		IMsEmployeeDao msEmployeeDao = (IMsEmployeeDao) getBean(IMsEmployeeDao.class);
		Pagination paginationSupport = msEmployeeDao.queryByCondition(msEmployeeQuery, 0, 15);
		logger.println(paginationSupport.getItems());

	}

	public void test_findById()
	{
		IMsEmployeeDao msEmployeeDao = (IMsEmployeeDao) getBean(IMsEmployeeDao.class);
		MsEmployee msEmployee = msEmployeeDao.findById(Integer.valueOf("2"));
		logger.println(msEmployee);
	}
}
