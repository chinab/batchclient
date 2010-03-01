package com.mindstatus.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

import com.mindstatus.bean.vo.MsPropList;
import com.mindstatus.bean.vo.MsPropType;
import com.mindstatus.bo.IOptionBo;
import com.vicutu.cache.Cache;
import com.vicutu.cache.CacheDataProvider;

public class OptionListProvider implements CacheDataProvider
{

	protected IOptionBo optionBo;

	protected boolean hasNullValue;

	protected static Logger logger = Logger.getLogger(OptionListProvider.class);

	public void setHasNullValue(boolean hasNullValue)
	{
		this.hasNullValue = hasNullValue;
	}

	public void setOptionBo(IOptionBo optionBo)
	{
		this.optionBo = optionBo;
	}

	public void clear(Cache cache)
	{
		cache.removeAll();
	}

	public void fill(Cache cache)
	{
		this.clear(cache);
		Map<String, List<MsPropList>> propListMap = optionBo.getMappedPropList();
		Set<Map.Entry<String, List<MsPropList>>> entris = propListMap.entrySet();
		Iterator<Map.Entry<String, List<MsPropList>>> entryIterator = entris.iterator();
		while (entryIterator.hasNext())
		{
			Map.Entry<String, List<MsPropList>> entry = entryIterator.next();
			String name = entry.getKey();
			List<MsPropList> msPropLists = entry.getValue();

			Iterator<MsPropList> msPropListIterator = msPropLists.iterator();
			List<LabelValueBean> options = new ArrayList<LabelValueBean>();
			if (!msPropLists.isEmpty() && hasNullValue)
			{
				options.add(new LabelValueBean("", ""));
			}
			while (msPropListIterator.hasNext())
			{
				MsPropList msPropList = msPropListIterator.next();
				String label = msPropList.getPropListName();
				String value = msPropList.getPropListId().toString();
				LabelValueBean labelValueBean = new LabelValueBean(label, value);
				options.add(labelValueBean);
			}
			logger.info("fill option:" + name);
			cache.put(name, options);
		}
	}

	public void update(Cache cache, Object parameter)
	{
		Integer propTypeId = (Integer) parameter;
		MsPropType msPropType = optionBo.findPropTypeById(propTypeId);
		List<MsPropList> msPropLists = optionBo.findPropListByTypeId(propTypeId);
		if (msPropLists != null)
		{
			List<LabelValueBean> options = new ArrayList<LabelValueBean>();
			if (!msPropLists.isEmpty() && hasNullValue)
			{
				options.add(new LabelValueBean("", ""));
			}
			Iterator<MsPropList> msPropListIterator = msPropLists.iterator();
			while (msPropListIterator.hasNext())
			{
				MsPropList msPropList = msPropListIterator.next();
				String label = msPropList.getPropListName();
				String value = msPropList.getPropListId().toString();
				LabelValueBean labelValueBean = new LabelValueBean(label, value);
				options.add(labelValueBean);
			}
			cache.put(msPropType.getPropTypeName(), options);
		}
		else
		{
			cache.remove(msPropType.getPropTypeName());
		}
	}
}
