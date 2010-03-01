package com.mindstatus.bo.impl;

import java.util.List;

import com.mindstatus.bean.dao.IMsEmployeeDao;
import com.mindstatus.bean.vo.MsEmployee;
import com.mindstatus.bo.IMsEmployeeBo;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

public class MsEmployeeBoImpl implements IMsEmployeeBo {

	protected IMsEmployeeDao msEmployeeDao;

	
	public List<MsEmployee> findByCondition(QueryTemplate queryTemplate) {
		return msEmployeeDao.findByCondition(queryTemplate);
	}

	
	public MsEmployee findById(Integer id) {
		return msEmployeeDao.findById(id);
	}

	
	public void saveOrUpdate(MsEmployee msEmployee) {
		msEmployeeDao.saveOrUpdate(msEmployee);

	}

	public void setMsEmployeeDao(IMsEmployeeDao msEmployeeDao) {
		this.msEmployeeDao = msEmployeeDao;
	}

	
	public Pagination findPageByCondition(QueryTemplate template,
			int pageSize, int startIndex) {
		return msEmployeeDao.findPageByCondition(template, pageSize, startIndex);
	}
	
	public void delete(MsEmployee msEmployee) {
		msEmployeeDao.delete(msEmployee);
	}

}
