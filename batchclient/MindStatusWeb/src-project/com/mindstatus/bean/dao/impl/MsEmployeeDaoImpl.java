package com.mindstatus.bean.dao.impl;

import java.util.List;

import com.mindstatus.bean.dao.IMsEmployeeDao;
import com.mindstatus.bean.vo.MsEmployee;
import com.vicutu.op.GenericDaoSupport;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

@SuppressWarnings("unchecked")
public class MsEmployeeDaoImpl extends GenericDaoSupport implements IMsEmployeeDao
{

	public List<MsEmployee> findAll()
	{
		return (List<MsEmployee>) list(MsEmployee.class);
	}

	public List<MsEmployee> findByCondition(QueryTemplate queryTemplate)
	{
		return (List<MsEmployee>) this.findAllByCriteria(queryTemplate.buildDetachedCriteria());
	}

	public MsEmployee findById(Integer id)
	{
		return (MsEmployee) this.getHibernateTemplate().get(MsEmployee.class, id);
	}

	public void saveOrUpdate(MsEmployee msEmployee)
	{
		this.getHibernateTemplate().saveOrUpdate(msEmployee);
	}

	public Pagination findPageByCondition(QueryTemplate template, int pageSize, int startIndex)
	{
		return this.findPageByCriteria(template.buildDetachedCriteria(), pageSize, startIndex);
	}

	public void delete(MsEmployee msEmployee)
	{
		this.getHibernateTemplate().delete(msEmployee);
	}
}
