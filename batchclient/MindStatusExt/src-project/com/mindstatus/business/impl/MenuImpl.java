package com.mindstatus.business.impl;

import java.util.List;

import com.mindstatus.business.IMenu;
import com.mindstatus.module.IMsMenuDao;
import com.mindstatus.module.MsMenu;

public class MenuImpl implements IMenu
{
	protected IMsMenuDao msMenuDao;

	public void setMsMenuDao(IMsMenuDao msMenuDao)
	{
		this.msMenuDao = msMenuDao;
	}

	@Override
	public List<MsMenu> findTreeNodeById(Integer parentId)
	{
		return msMenuDao.findByProperty("parentId", parentId);
	}
}
