package com.mindstatus.module.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindstatus.module.IMsPropListDao;
import com.mindstatus.module.MsPropList;

@SuppressWarnings("unchecked")
public class MsPropListDaoImpl extends HibernateDaoSupport implements IMsPropListDao
{
	public List<MsPropList> findAll()
	{
		Criteria criteria = getSession().createCriteria(MsPropList.class);
		return criteria.list();
	}

	public List<MsPropList> findByTypeId(Integer id)
	{
		MsPropList msPropListQuery = new MsPropList();
		msPropListQuery.setPropTypeId(id);
		return (List<MsPropList>) this.getHibernateTemplate().findByExample(msPropListQuery);
	}

	public void saveOrUpdate(MsPropList msPropList)
	{
		this.getHibernateTemplate().saveOrUpdate(msPropList);
	}
}
