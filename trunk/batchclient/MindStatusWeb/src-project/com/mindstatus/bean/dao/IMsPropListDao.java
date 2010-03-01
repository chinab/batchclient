package com.mindstatus.bean.dao;

import java.util.List;

import com.mindstatus.bean.vo.MsPropList;

public interface IMsPropListDao {

	List<MsPropList> findByTypeId(Integer id);
	
	List<MsPropList> findAll();
	
	void saveOrUpdate(MsPropList msPropList);
}
