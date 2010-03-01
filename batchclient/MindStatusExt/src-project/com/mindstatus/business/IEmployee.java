package com.mindstatus.business;

import com.mindstatus.module.MsEmployee;
import com.mindstatus.module.query.MsEmployeeQuery;
import com.vicutu.persistence.query.Pagination;

public interface IEmployee
{
	void saveOrUpdate(MsEmployee msEmployee);

	MsEmployee findById(Integer id);

	void delete(MsEmployee msEmployee);

	public Pagination queryByCondition(MsEmployeeQuery msEmployeeQuery, int start, int limit);
}
