package com.vicutu.op.query;

import org.hibernate.criterion.DetachedCriteria;

public interface IPaginationSupport {
	public Pagination findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex);
}
