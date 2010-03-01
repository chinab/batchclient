package com.mindstatus.module;

import java.util.List;

import com.mindstatus.module.MsUser;

public interface IMsUserDao
{
	List<MsUser> findByUserName(String userName);

	void saveOrUpdate(MsUser msUser);

	void delete(MsUser msUser);
}
