package com.mindstatus.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.mindstatus.business.IDictionary;
import com.mindstatus.module.MsPropList;
import com.mindstatus.module.MsPropType;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.services.dictionary.DictionaryBean;
import com.vicutu.services.dictionary.DictionaryOperator;

public class DefaultDictionaryOperatorImpl implements DictionaryOperator
{
	private static Logger logger = LoggerFactory.getLogger(DictionaryOperator.class);

	protected IDictionary dictionary;

	public void setDictionary(IDictionary dictionary)
	{
		this.dictionary = dictionary;
	}

	public Map<String, List<DictionaryBean>> getDictionarys()
	{
		Map<String, List<DictionaryBean>> dictionarys = new HashMap<String, List<DictionaryBean>>();

		Map<String, List<MsPropList>> propListMap = dictionary.getMappedPropList();

		Set<Entry<String, List<MsPropList>>> entris = propListMap.entrySet();
		Iterator<Map.Entry<String, List<MsPropList>>> entryIterator = entris.iterator();
		while (entryIterator.hasNext())
		{
			Entry<String, List<MsPropList>> entry = entryIterator.next();
			String name = entry.getKey();
			List<MsPropList> msPropLists = entry.getValue();
			Iterator<MsPropList> msPropListIterator = msPropLists.iterator();
			List<DictionaryBean> dictionaryBeans = new ArrayList<DictionaryBean>();
			while (msPropListIterator.hasNext())
			{
				MsPropList msPropList = msPropListIterator.next();
				String label = msPropList.getPropListName();
				String value = msPropList.getPropListId().toString();
				DictionaryBean labelValueBean = new DictionaryBean(label, value);
				dictionaryBeans.add(labelValueBean);
			}
			dictionarys.put(name, dictionaryBeans);
			logger.info("load dictionary : {}", name);
		}
		return dictionarys;
	}

	public Map<String, List<DictionaryBean>> getDictionarys(Object parameter)
	{
		Map<String, List<DictionaryBean>> dictionarys = new HashMap<String, List<DictionaryBean>>();

		Integer propTypeId = (Integer) parameter;
		MsPropType msPropType = dictionary.findPropTypeById(propTypeId);
		List<MsPropList> msPropLists = dictionary.findPropListByTypeId(propTypeId);
		if (msPropLists != null)
		{
			List<DictionaryBean> dictionaryBeans = new ArrayList<DictionaryBean>();
			Iterator<MsPropList> msPropListIterator = msPropLists.iterator();
			while (msPropListIterator.hasNext())
			{
				MsPropList msPropList = msPropListIterator.next();
				String label = msPropList.getPropListName();
				String value = msPropList.getPropListId().toString();
				DictionaryBean labelValueBean = new DictionaryBean(label, value);
				dictionaryBeans.add(labelValueBean);
			}
			dictionarys.put(msPropType.getPropTypeName(), dictionaryBeans);
			logger.info("reload dictionary : {}", msPropType.getPropTypeName());
		}
		else
		{
			dictionarys.put(msPropType.getPropTypeName(), null);
			logger.info("reload dictionary with empty value -- {}", msPropType.getPropTypeName());
		}

		return dictionarys;
	}
}
