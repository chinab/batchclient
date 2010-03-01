package com.mindstatus.bean.dao.impl;

import java.util.List;

import com.mindstatus.bean.dao.IMsPropTypeDao;
import com.mindstatus.bean.vo.MsPropType;
import com.vicutu.op.GenericDaoSupport;

@SuppressWarnings("unchecked")
public class MsPropTypeDaoImpl extends GenericDaoSupport implements IMsPropTypeDao
{
	public List<MsPropType> findAll()
	{
		return (List<MsPropType>) list(MsPropType.class);
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
