package com.mindstatus.bean.dao.impl;

import java.util.List;

import com.mindstatus.bean.dao.IMsUserDao;
import com.mindstatus.bean.vo.MsUser;
import com.vicutu.op.GenericDaoSupport;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

@SuppressWarnings("unchecked")
public class MsUserDaoImpl extends GenericDaoSupport implements IMsUserDao
{
	public List<MsUser> findByUserName(String userName)
	{
		MsUser msUser = new MsUser();
		msUser.setUserName(userName);
		return (List<MsUser>) this.getHibernateTemplate().findByExample(msUser);
	}

	public void saveOrUpdate(MsUser msUser)
	{
		this.getHibernateTemplate().saveOrUpdate(msUser);
	}

	public Pagination findPageByCondition(QueryTemplate template, int pageSize, int startIndex)
	{
		return findPageByCriteria(template.buildDetachedCriteria(), pageSize, startIndex);
	}

	public void delete(MsUser msUser)
	{
		this.getHibernateTemplate().delete(msUser);
	}
}
