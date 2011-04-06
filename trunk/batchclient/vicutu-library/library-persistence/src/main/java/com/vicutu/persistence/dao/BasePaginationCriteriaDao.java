package com.vicutu.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.vicutu.persistence.query.Pagination;

public interface BasePaginationCriteriaDao<E> {

	List<E> queryByCriteria(DetachedCriteria detachedCriteria);

	int countByCriteria(DetachedCriteria detachedCriteria);

	Pagination<E> queryByCriteria(DetachedCriteria detachedCriteria, int start, int limit);

	List<E> queryByCriteria(Object condition);

	int countByCriteria(Object condition);

	Pagination<E> queryByCriteria(Object condition, int start, int limit);
}
