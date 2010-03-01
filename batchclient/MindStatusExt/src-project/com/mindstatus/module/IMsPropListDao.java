package com.mindstatus.module;

import java.util.List;

import com.mindstatus.module.MsPropList;

public interface IMsPropListDao
{

	List<MsPropList> findByTypeId(Integer id);

	List<MsPropList> findAll();

	void saveOrUpdate(MsPropList msPropList);
}
