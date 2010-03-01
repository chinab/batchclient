package com.mindstatus.bo;

import java.sql.Timestamp;

import com.mindstatus.bean.vo.MsUser;
import com.vicutu.cache.CacheManager;
import com.vicutu.op.query.QueryTemplate;
import com.vicutu.test.SpringBaseTestCase;

public class MsUserTestCase extends SpringBaseTestCase {
	
	public void test_regist(){
		for(int i=0;i<100;i++){
		MsUser msUser=new MsUser();
		msUser.setUserName("user"+i);
		msUser.setSex(new Integer(1));
		msUser.setPassword("a");
		msUser.setRealName("普通用户1");
		msUser.setRegTime(new Timestamp(System.currentTimeMillis()));
		msUser.setLoginTimes(new Integer(0));
		IMsUserBo msUserBo=(IMsUserBo)getBean("msUserBoProxy");
		msUserBo.regist(msUser);
		}
	}
	
	public void test_logon(){
		MsUser msUser=new MsUser();
		msUser.setUserName("admin");
		msUser.setPassword("a");
		IMsUserBo msUserBo=(IMsUserBo)getBean("msUserBoProxy");
		logger.println(msUserBo.logon(msUser));
		logger.println(msUser);
	}
	
	public void test_query(){
		IMsUserBo msUserBo=(IMsUserBo)getBean("msUserBoProxy");
		CacheManager cacheManager=(CacheManager)getBean("cacheManager");
		QueryTemplate queryTemplate = (QueryTemplate) cacheManager.get(
		"queryTemplateCache").get("MsUser");
		logger.println(msUserBo.findPageByCondition(queryTemplate, 10, 0).getItems());
	}
	
	public void test_transcation(){
	}

}
