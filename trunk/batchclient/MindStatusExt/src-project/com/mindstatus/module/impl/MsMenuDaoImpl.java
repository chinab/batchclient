package com.mindstatus.module.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindstatus.module.IMsMenuDao;
import com.mindstatus.module.MsMenu;

/**
 * A data access object (DAO) providing persistence and search support for
 * Primarykeycn entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hrm.houfei.po.Primarykeycn
 * @author MyEclipse Persistence Tools
 */

public class MsMenuDaoImpl extends HibernateDaoSupport implements IMsMenuDao
{
	public void save(MsMenu transientInstance)
	{
		getHibernateTemplate().save(transientInstance);
	}

	public void delete(MsMenu persistentInstance)
	{
		getHibernateTemplate().delete(persistentInstance);
	}

	public MsMenu findById(Integer id)
	{
		MsMenu instance = (MsMenu) getHibernateTemplate().get(MsMenu.class, id);
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<MsMenu> findByExample(MsMenu instance)
	{
		List<MsMenu> results = getHibernateTemplate().findByExample(instance);
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<MsMenu> findByProperty(String propertyName, Object value)
	{
		String queryString = "from MsMenu as model where model." + propertyName + "= ?";
		return getHibernateTemplate().find(queryString, value);
	}

	@SuppressWarnings("unchecked")
	public List<MsMenu> findAll()
	{
		String queryString = "from MsMenu";
		return getHibernateTemplate().find(queryString);
	}
}