package com.mindstatus.business;

import java.util.List;

import com.mindstatus.module.MsMenu;

public interface IMenu
{
	public List<MsMenu> findTreeNodeById(Integer parentId);
}
