package com.vicutu.persistence.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.vicutu.persistence.dao.BaseCrudDao;
import com.vicutu.persistence.dao.BasePaginationCriteriaDao;
import com.vicutu.persistence.query.DetachedCriteriaBuilder;
import com.vicutu.persistence.query.Pagination;

public class HibernateGenericDaoSupport<E> extends HibernateCrudDaoSupport<E> implements BasePaginationCriteriaDao<E>,
		BaseCrudDao<E> {

	private DetachedCriteriaBuilder detachedCriteriaBuilder;

	@Autowired
	public void setDetachedCriteriaBuilder(DetachedCriteriaBuilder detachedCriteriaBuilder) {
		this.detachedCriteriaBuilder = detachedCriteriaBuilder;
	}

	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> queryByCriteria(final DetachedCriteria detachedCriteria) {
		return (List<E>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public int countByCriteria(final DetachedCriteria detachedCriteria) {
		Integer count = (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		});
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<E> queryByCriteria(final DetachedCriteria detachedCriteria, final int start, final int limit) {
		return (Pagination<E>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				criteria.setProjection(null);
				List<?> items = criteria.setFirstResult(start).setMaxResults(limit).list();
				Pagination ps = new Pagination(items, totalCount, limit, start);
				return ps;
			}
		});
	}

	@Override
	public List<E> queryByCriteria(Object condition) {
		return this.queryByCriteria(this.buildDetachedCriteria(condition));
	}

	@Override
	public int countByCriteria(Object condition) {
		return this.countByCriteria(this.buildDetachedCriteria(condition));
	}

	@Override
	public Pagination<E> queryByCriteria(Object condition, int start, int limit) {
		return this.queryByCriteria(this.buildDetachedCriteria(condition), start, limit);
	}

	private DetachedCriteria buildDetachedCriteria(Object condition) {
		return detachedCriteriaBuilder.build(condition);
	}
}
