package com.vicutu.op;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vicutu.op.query.IListSupport;
import com.vicutu.op.query.IPaginationSupport;
import com.vicutu.op.query.Pagination;

public class GenericDaoSupport extends HibernateDaoSupport implements
		IListSupport, IPaginationSupport {

	public List<?> list(Class<?> clazz) {
		Criteria criteria = getSession().createCriteria(clazz);
		return criteria.list();
	}

	public List<?> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		return (List<?>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return criteria.list();
					}
				}, true);
	}

	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Integer count = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return criteria.setProjection(Projections.rowCount())
								.uniqueResult();
					}
				}, true);
		return count.intValue();
	}

	public Pagination findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex) {
		return (Pagination) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						int totalCount = ((Integer) criteria.setProjection(
								Projections.rowCount()).uniqueResult())
								.intValue();
						criteria.setProjection(null);
						List<?> items = criteria.setFirstResult(startIndex)
								.setMaxResults(pageSize).list();
						Pagination ps = new Pagination(items, totalCount,
								pageSize, startIndex);
						return ps;
					}
				}, true);
	}
}
