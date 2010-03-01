package com.mindstatus.module.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindstatus.module.IMsUserDao;
import com.mindstatus.module.MsUser;

@SuppressWarnings("unchecked")
public class MsUserDaoImpl extends HibernateDaoSupport implements IMsUserDao
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

	public void delete(MsUser msUser)
	{
		this.getHibernateTemplate().delete(msUser);
	}
}
