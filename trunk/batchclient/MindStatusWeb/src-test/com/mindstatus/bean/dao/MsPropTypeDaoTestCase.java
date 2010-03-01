package com.mindstatus.bean.dao;

import java.util.Iterator;
import java.util.List;

import com.mindstatus.bean.vo.MsPropType;
import com.vicutu.test.SpringBaseTestCase;


public class MsPropTypeDaoTestCase extends SpringBaseTestCase {
	
	public void test_findAll(){
		IMsPropTypeDao msPropTypeDao=(IMsPropTypeDao)getBean("msPropTypeDaoProxy");
		List<MsPropType> msPropTypeList=msPropTypeDao.findAll();
		Iterator<MsPropType> msPropTypeIt= msPropTypeList.iterator();
		while(msPropTypeIt.hasNext()){
			logger.println(msPropTypeIt.next());
		}
	}
}
