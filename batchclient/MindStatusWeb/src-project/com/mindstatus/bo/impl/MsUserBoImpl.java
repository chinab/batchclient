package com.mindstatus.bo.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.mindstatus.bean.dao.IMsUserDao;
import com.mindstatus.bean.vo.MsUser;
import com.mindstatus.bo.IMsUserBo;
import com.vicutu.commons.crypto.nor.Encipher;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

public class MsUserBoImpl implements IMsUserBo {

	protected IMsUserDao msUserDao;

	
	public Pagination findPageByCondition(QueryTemplate template,
			int pageSize, int startIndex) {
		return msUserDao.findPageByCondition(template, pageSize, startIndex);
	}

	
	public int logon(MsUser msUser) {
		List<MsUser> msUserList = msUserDao
				.findByUserName(msUser.getUserName());
		if (msUserList != null && msUserList.size() > 0) {
			MsUser user = msUserList.get(0);
			String password = user.getPassword();
			if (msUser.getPassword().trim().equals(
					Encipher.DecodePasswd(password))) {
				Integer logonTimes = user.getLoginTimes();
				user.setLoginTimes(new Integer(logonTimes.intValue() + 1));
				msUserDao.saveOrUpdate(user);
				try {
					PropertyUtils.copyProperties(msUser, user);
				} catch (Exception e) {
					throw new RuntimeException();
				}
				return LOGON_CODE_SUCCESS;
			} else {
				return LOGON_CODE_PASSWORD_ERR;
			}
		} else {
			return LOGON_CODE_USER_NOT_EXIST_ERR;
		}
	}

	
	public int regist(MsUser msUser) {

		List<MsUser> alreadyMsUserList = msUserDao.findByUserName(msUser
				.getUserName());
		if (alreadyMsUserList != null && alreadyMsUserList.size() > 0) {
			return REGIST_CODE_USER_ALREADY_EXIST_ERR;
		}
		msUser.setPassword(Encipher.EncodePasswd(msUser.getPassword()));
		msUser.setLoginTimes((new Integer(0)));
		if (msUser.getRegTime() == null) {
			msUser.setRegTime(new Timestamp(System.currentTimeMillis()));
		}
		msUserDao.saveOrUpdate(msUser);
		return REGIST_CODE_SUCCESS;
	}

	public void modify(MsUser msUser) {
		msUser.setPassword(Encipher.EncodePasswd(msUser.getPassword()));
		msUserDao.saveOrUpdate(msUser);
	}

	public void delete(MsUser msUser) {
		msUserDao.delete(msUser);
	}

	public void setMsUserDao(IMsUserDao msUserDao) {
		this.msUserDao = msUserDao;
	}
}
