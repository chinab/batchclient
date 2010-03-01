package com.mindstatus.bo;

import java.util.List;

import com.mindstatus.bean.vo.MsEmployee;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

public interface IMsEmployeeBo {
	void saveOrUpdate(MsEmployee msEmployee);

	MsEmployee findById(Integer id);
	
	void delete(MsEmployee msEmployee);

	List<MsEmployee> findByCondition(QueryTemplate queryTemplate);
	
	public Pagination findPageByCondition(QueryTemplate template,
			int pageSize, int startIndex);
}
