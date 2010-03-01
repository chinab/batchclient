package com.mindstatus.business.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mindstatus.business.IDictionary;
import com.mindstatus.module.IMsPropListDao;
import com.mindstatus.module.IMsPropTypeDao;
import com.mindstatus.module.MsPropList;
import com.mindstatus.module.MsPropType;

public class DictionaryImpl implements IDictionary
{
	protected IMsPropTypeDao msPropTypeDao;

	protected IMsPropListDao msPropListDao;

	public void setMsPropTypeDao(IMsPropTypeDao msPropTypeDao)
	{
		this.msPropTypeDao = msPropTypeDao;
	}

	public void setMsPropListDao(IMsPropListDao msPropListDao)
	{
		this.msPropListDao = msPropListDao;
	}

	public Map<String, List<MsPropList>> getMappedPropList()
	{
		Map<String, List<MsPropList>> propListMap = new HashMap<String, List<MsPropList>>();

		List<MsPropType> msPropTypeList = msPropTypeDao.findAll();
		Iterator<MsPropType> msPropTypeIterator = msPropTypeList.iterator();
		while (msPropTypeIterator.hasNext())
		{
			MsPropType msPropType = msPropTypeIterator.next();

			Integer propTypeId = msPropType.getPropTypeId();
			List<MsPropList> msPropList = msPropListDao.findByTypeId(propTypeId);
			String propTypeName = msPropType.getPropTypeName();
			if (msPropList != null)
			{
				propListMap.put(propTypeName, msPropList);
			}
		}
		return propListMap;
	}

	public List<MsPropType> findAllPropType()
	{
		return msPropTypeDao.findAll();
	}

	public List<MsPropList> findPropListByTypeId(Integer propTypeId)
	{
		return msPropListDao.findByTypeId(propTypeId);
	}

	public void saveOrUpdateMsPropList(MsPropList msPropList)
	{

		msPropListDao.saveOrUpdate(msPropList);
	}

	public void saveOrUpdateMsPropType(MsPropType msPropType)
	{
		msPropTypeDao.saveOrUpdate(msPropType);

	}

	public List<MsPropList> findPropListByTypeName(String propTypeName)
	{
		MsPropType msPropType = msPropTypeDao.findByName(propTypeName);
		if (msPropType != null)
		{
			return msPropListDao.findByTypeId(msPropType.getPropTypeId());
		}
		else
		{
			return null;
		}
	}

	public MsPropType findPropTypeById(Integer propTypeId)
	{
		return msPropTypeDao.findById(propTypeId);
	}
}
