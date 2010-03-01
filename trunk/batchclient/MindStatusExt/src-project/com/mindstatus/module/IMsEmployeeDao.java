package com.mindstatus.module;

import java.util.List;

import com.mindstatus.module.query.MsEmployeeQuery;
import com.vicutu.persistence.query.Pagination;

public interface IMsEmployeeDao
{
	void saveOrUpdate(MsEmployee msEmployee);

	void delete(MsEmployee msEmployee);

	List<MsEmployee> findAll();

	MsEmployee findById(Integer id);
	
	Pagination queryByCondition(MsEmployeeQuery msEmployeeQuery, int start, int limit);
}
