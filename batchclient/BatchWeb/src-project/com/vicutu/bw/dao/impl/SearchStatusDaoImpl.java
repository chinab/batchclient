package com.vicutu.bw.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vicutu.bw.dao.SearchStatusDao;
import com.vicutu.bw.vo.SearchStatus;
import com.vicutu.persistence.dao.impl.HibernateGenericDaoSupport;

@Repository
@Transactional
public class SearchStatusDaoImpl extends HibernateGenericDaoSupport<SearchStatus> implements SearchStatusDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SearchStatus findSearchStatusByName(String accessName) {
		List<SearchStatus> list = this.getSession().createCriteria(SearchStatus.class).add(
				Restrictions.eq("accessName", accessName)).list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdateSearchStatus(SearchStatus searchStatus) {
		this.saveOrUpdate(searchStatus);
	}
}
