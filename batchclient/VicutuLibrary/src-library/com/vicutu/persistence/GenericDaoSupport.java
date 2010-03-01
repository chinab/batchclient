package com.vicutu.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vicutu.persistence.query.Pagination;
import com.vicutu.persistence.query.QueryTemplate;
import com.vicutu.persistence.query.QueryTemplateManager;

public class GenericDaoSupport extends HibernateDaoSupport
{
	private QueryTemplateManager queryTemplateManager;

	public void setQueryTemplateManager(QueryTemplateManager queryTemplateManager)
	{
		this.queryTemplateManager = queryTemplateManager;
	}

	public List<?> list(Class<?> clazz)
	{
		Criteria criteria = getSession().createCriteria(clazz);
		return criteria.list();
	}

	public List<?> queryByCriteria(final DetachedCriteria detachedCriteria)
	{
		return (List<?>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException
			{
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.list();
			}
		});
	}

	public int countByCriteria(final DetachedCriteria detachedCriteria)
	{
		Integer count = (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException
			{
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		});
		return count.intValue();
	}

	public Pagination queryByCriteria(final DetachedCriteria detachedCriteria, final int start, final int limit)
	{
		return (Pagination) getHibernateTemplate().executeWithNativeSession(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException
			{
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				criteria.setProjection(null);
				List<?> items = criteria.setFirstResult(start).setMaxResults(limit).list();
				Pagination ps = new Pagination(items, totalCount, limit, start);
				return ps;
			}
		});
	}

	public List<?> queryByCriteria(Object condition)
	{
		return this.queryByCriteria(this.buildDetachedCriteria(condition));
	}

	public int countByCriteria(Object condition)
	{
		return this.countByCriteria(this.buildDetachedCriteria(condition));
	}

	public Pagination queryByCriteria(Object condition, int start, int limit)
	{
		return this.queryByCriteria(this.buildDetachedCriteria(condition), start, limit);
	}

	private DetachedCriteria buildDetachedCriteria(Object condition)
	{
		QueryTemplate queryTemplate = queryTemplateManager.getQueryTemplate(condition.getClass());
		queryTemplate.setValues(condition);
		return queryTemplate.buildDetachedCriteria();
	}
}
