package com.mindstatus.bean.dao;

import java.util.List;

import com.mindstatus.bean.vo.MsEmployee;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

public interface IMsEmployeeDao {

	void saveOrUpdate(MsEmployee msEmployee);
	
	void delete(MsEmployee msEmployee);

	List<MsEmployee> findAll();

	MsEmployee findById(Integer id);
	
	List<MsEmployee> findByCondition(QueryTemplate queryTemplate);
	
	Pagination findPageByCondition(QueryTemplate template,final int pageSize,   
            final int startIndex);
}
