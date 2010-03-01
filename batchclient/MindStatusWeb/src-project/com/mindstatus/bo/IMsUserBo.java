package com.mindstatus.bo;

import com.mindstatus.bean.vo.MsUser;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

public interface IMsUserBo {
	
	int LOGON_CODE_SUCCESS=0;
	
	int LOGON_CODE_USER_NOT_EXIST_ERR=1;
	
	int LOGON_CODE_PASSWORD_ERR=2;
	
	int REGIST_CODE_SUCCESS=0;
	
	int REGIST_CODE_USER_ALREADY_EXIST_ERR=1;
	
	int logon(MsUser msUser);
	
	int regist(MsUser msUser);
	
	void modify(MsUser msUser);
	
	void delete(MsUser msUser);	
	
	Pagination findPageByCondition(QueryTemplate template,final int pageSize,   
            final int startIndex);

}
