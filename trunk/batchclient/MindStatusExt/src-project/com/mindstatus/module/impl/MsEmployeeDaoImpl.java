package com.mindstatus.module.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.mindstatus.module.IMsEmployeeDao;
import com.mindstatus.module.MsEmployee;
import com.mindstatus.module.query.MsEmployeeQuery;
import com.vicutu.persistence.GenericDaoSupport;
import com.vicutu.persistence.query.Pagination;

@SuppressWarnings("unchecked")
public class MsEmployeeDaoImpl extends GenericDaoSupport implements IMsEmployeeDao
{

	public List<MsEmployee> findAll()
	{
		Criteria criteria = getSession().createCriteria(MsEmployee.class);
		return criteria.list();
	}

	public MsEmployee findById(Integer id)
	{
		return (MsEmployee) this.getHibernateTemplate().get(MsEmployee.class, id);
	}

	public void saveOrUpdate(MsEmployee msEmployee)
	{
		this.getHibernateTemplate().saveOrUpdate(msEmployee);
	}

	public void delete(MsEmployee msEmployee)
	{
		this.getHibernateTemplate().delete(msEmployee);
	}

	@Override
	public Pagination queryByCondition(MsEmployeeQuery msEmployeeQuery, int start, int limit)
	{
		return this.queryByCriteria(msEmployeeQuery, start, limit);
	}
}
