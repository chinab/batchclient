package com.vicutu.op.query;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IListSupport {
	
	List<?> list(Class<?> clazz);
	
	List<?> findAllByCriteria(final DetachedCriteria detachedCriteria);
	
	int getCountByCriteria(final DetachedCriteria detachedCriteria);

}
