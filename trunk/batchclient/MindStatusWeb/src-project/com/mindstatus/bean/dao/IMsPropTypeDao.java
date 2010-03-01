package com.mindstatus.bean.dao;

import java.util.List;

import com.mindstatus.bean.vo.MsPropType;

public interface IMsPropTypeDao {
	
	void saveOrUpdate(MsPropType msPropType);
	
	List<MsPropType> findAll();
	
	MsPropType findById(Integer id);
	
	MsPropType findByName(String propTypeName);

}
