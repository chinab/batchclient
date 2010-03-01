package com.mindstatus.business.impl;

import com.mindstatus.business.IEmployee;
import com.mindstatus.module.IMsEmployeeDao;
import com.mindstatus.module.MsEmployee;
import com.mindstatus.module.query.MsEmployeeQuery;
import com.vicutu.persistence.query.Pagination;

public class EmployeeImpl implements IEmployee
{
	protected IMsEmployeeDao msEmployeeDao;
	
	public void setMsEmployeeDao(IMsEmployeeDao msEmployeeDao)
	{
		this.msEmployeeDao = msEmployeeDao;
	}

	public void delete(MsEmployee msEmployee)
	{
		msEmployeeDao.delete(msEmployee);
		
	}

	public MsEmployee findById(Integer id)
	{
		return msEmployeeDao.findById(id);
	}

	public Pagination queryByCondition(MsEmployeeQuery msEmployeeQuery, int start, int limit)
	{
		return msEmployeeDao.queryByCondition(msEmployeeQuery, start, limit);
	}

	public void saveOrUpdate(MsEmployee msEmployee)
	{
		msEmployeeDao.saveOrUpdate(msEmployee);
		
	}
}
