package com.mindstatus.module.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindstatus.module.IMsPropTypeDao;
import com.mindstatus.module.MsPropType;

@SuppressWarnings("unchecked")
public class MsPropTypeDaoImpl extends HibernateDaoSupport implements IMsPropTypeDao
{
	public List<MsPropType> findAll()
	{
		Criteria criteria = getSession().createCriteria(MsPropType.class);
		return criteria.list();
	}

	public MsPropType findById(Integer id)
	{
		return (MsPropType) this.getHibernateTemplate().get(MsPropType.class, id);
	}

	public void saveOrUpdate(MsPropType msPropType)
	{
		this.getHibernateTemplate().saveOrUpdate(msPropType);
	}

	public MsPropType findByName(String propTypeName)
	{
		MsPropType msPropTypeExample = new MsPropType();
		msPropTypeExample.setPropTypeName(propTypeName);
		List<MsPropType> msPropTypeList = (List<MsPropType>) this.getHibernateTemplate().findByExample(msPropTypeExample);
		if (msPropTypeList != null && msPropTypeList.size() > 0)
		{
			return msPropTypeList.get(0);
		}
		else
		{
			return null;
		}
	}
}
