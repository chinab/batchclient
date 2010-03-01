package com.mindstatus.bean.dao;

import java.util.List;

import com.mindstatus.bean.vo.MsUser;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

public interface IMsUserDao {
	
	List<MsUser> findByUserName(String userName);
	
	void saveOrUpdate(MsUser msUser);
	
	void delete(MsUser msUser);
	
	Pagination findPageByCondition(QueryTemplate template,final int pageSize,   
            final int startIndex);

}
