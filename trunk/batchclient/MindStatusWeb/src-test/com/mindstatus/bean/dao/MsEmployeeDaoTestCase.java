package com.mindstatus.bean.dao;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import com.mindstatus.bean.query.MsEmployeeQuery;
import com.mindstatus.bean.vo.MsEmployee;
import com.vicutu.cache.CacheManager;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;
import com.vicutu.op.query.QueryTemplateManager;
import com.vicutu.test.SpringBaseTestCase;

public class MsEmployeeDaoTestCase extends SpringBaseTestCase {
	
	public void test_findAll(){
		IMsEmployeeDao msEmployeeDao=(IMsEmployeeDao)getBean("msEmployeeDaoProxy");
		List<MsEmployee> msEmployeeList=msEmployeeDao.findAll();
		Iterator<MsEmployee> msEmployeeIt= msEmployeeList.iterator();
		while(msEmployeeIt.hasNext()){
			logger.println(msEmployeeIt.next());
		}
	}
	
	public void test_saveOrUpdate(){
		for(int i=0;i<2000;i++){
			MsEmployee msEmployee=new MsEmployee();
			msEmployee.setName("z"+i);
			msEmployee.setSex(new Integer(2));
			msEmployee.setAge(new Integer(24));
			msEmployee.setCampaign(new Integer(1));
			msEmployee.setCommunity(new Integer(2));
			msEmployee.setBirthday(new Timestamp(System.currentTimeMillis()));
			IMsEmployeeDao msEmployeeDao=(IMsEmployeeDao)getBean("msEmployeeDaoProxy");
			msEmployeeDao.saveOrUpdate(msEmployee);
		}
		
	}
	
	public void test_findByCondition1(){
		QueryTemplateManager qtManager=(QueryTemplateManager)getBean("queryTemplateManager");
		QueryTemplate qt=qtManager.getQueryTemplate("MsEmployee");
		qt.setValue(2, "��");
		IMsEmployeeDao msEmployeeDao=(IMsEmployeeDao)getBean("msEmployeeDaoProxy");
		List<MsEmployee> msEmployeeList=msEmployeeDao.findByCondition(qt);
		Iterator<MsEmployee> msEmployeeIt= msEmployeeList.iterator();
		while(msEmployeeIt.hasNext()){
			logger.println(msEmployeeIt.next());
		}
	}
	
	public void test_findByCondition2(){
		QueryTemplateManager qtManager=(QueryTemplateManager)getBean("queryTemplateManager");
		QueryTemplate qt=qtManager.getQueryTemplate("MsEmployee");
		qt.setValue("bithdayEnd", new Timestamp(System.currentTimeMillis()));
		IMsEmployeeDao msEmployeeDao=(IMsEmployeeDao)getBean("msEmployeeDaoProxy");
		List<MsEmployee> msEmployeeList=msEmployeeDao.findByCondition(qt);
		Iterator<MsEmployee> msEmployeeIt= msEmployeeList.iterator();
		while(msEmployeeIt.hasNext()){
			logger.println(msEmployeeIt.next());
		}
	}
	
	public void test_findByCondition3(){
		MsEmployeeQuery msEmployeeQuery=new MsEmployeeQuery();
		
		msEmployeeQuery.setBirthdayEnd(new Timestamp(System.currentTimeMillis()));
		msEmployeeQuery.setName("��");
		
		QueryTemplateManager qtManager=(QueryTemplateManager)getBean("queryTemplateManager");
		QueryTemplate qt=qtManager.getQueryTemplate("MsEmployee");
		qt.setValues(msEmployeeQuery);
		IMsEmployeeDao msEmployeeDao=(IMsEmployeeDao)getBean("msEmployeeDaoProxy");
		List<MsEmployee> msEmployeeList=msEmployeeDao.findByCondition(qt);
		Iterator<MsEmployee> msEmployeeIt= msEmployeeList.iterator();
		while(msEmployeeIt.hasNext()){
			logger.println(msEmployeeIt.next());
		}
	}
	
	public void test_findPageByCondition(){
		MsEmployeeQuery msEmployeeQuery=new MsEmployeeQuery();
		CacheManager cacheManager=(CacheManager)getBean("cacheManager");
		IMsEmployeeDao msEmployeeDao=(IMsEmployeeDao)getBean("msEmployeeDaoProxy");
		QueryTemplate queryTemplate=(QueryTemplate)cacheManager.get("queryTemplateCache").get("MsEmployee");
		queryTemplate.setValues(msEmployeeQuery);
		
		Pagination paginationSupport=msEmployeeDao.findPageByCondition(queryTemplate, 2, 0);
		logger.println(paginationSupport.getItems());
		
	}
	
	public void test_findById(){
		IMsEmployeeDao msEmployeeDao=(IMsEmployeeDao)getBean("msEmployeeDaoProxy");
		MsEmployee msEmployee=msEmployeeDao.findById(Integer.valueOf("2"));
		logger.println(msEmployee);
	}

}
