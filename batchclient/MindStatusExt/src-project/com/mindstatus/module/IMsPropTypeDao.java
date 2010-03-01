package com.mindstatus.module;

import java.util.List;

import com.mindstatus.module.MsPropType;

public interface IMsPropTypeDao
{

	void saveOrUpdate(MsPropType msPropType);

	List<MsPropType> findAll();

	MsPropType findById(Integer id);

	MsPropType findByName(String propTypeName);

}
