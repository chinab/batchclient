package com.vicutu.bw.dao.db.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vicutu.bw.dao.AccessDetailDao;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.persistence.dao.impl.HibernateGenericDaoSupport;

@Repository
@Transactional
public class AccessDetailDaoImpl extends HibernateGenericDaoSupport<AccessDetail> implements AccessDetailDao {

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<AccessDetail> findAllAccessDetail() {
		return this.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public AccessDetail findAccessDetailByName(String name) {
		List<AccessDetail> list = this.getSession().createCriteria(AccessDetail.class)
				.add(Restrictions.eq("name", name)).list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdateAccessDetail(AccessDetail accessDetail) {
		this.saveOrUpdate(accessDetail);
	}
}
