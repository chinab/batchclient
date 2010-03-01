package com.mindstatus.bean.dao.impl;

import java.util.List;

import com.mindstatus.bean.dao.IMsPropListDao;
import com.mindstatus.bean.vo.MsPropList;
import com.vicutu.op.GenericDaoSupport;

@SuppressWarnings("unchecked")
public class MsPropListDaoImpl extends GenericDaoSupport implements IMsPropListDao
{
	public List<MsPropList> findAll()
	{
		return (List<MsPropList>) list(MsPropList.class);
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
