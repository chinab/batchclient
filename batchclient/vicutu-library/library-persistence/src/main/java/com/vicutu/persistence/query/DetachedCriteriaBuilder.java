package com.vicutu.persistence.query;

import org.hibernate.criterion.DetachedCriteria;

public interface DetachedCriteriaBuilder {
	DetachedCriteria build(Object source);
}
