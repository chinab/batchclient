package com.mindstatus.module;

import java.util.List;

public interface IMsMenuDao
{
	public abstract void save(MsMenu transientInstance);

	public abstract void delete(MsMenu persistentInstance);

	public abstract MsMenu findById(java.lang.Integer id);

	public abstract List<MsMenu> findByExample(MsMenu instance);

	public abstract List<MsMenu> findByProperty(String propertyName, Object value);

	public abstract List<MsMenu> findAll();
}