package com.vicutu.services.dictionary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.vicutu.commons.lang.IServiceable;
import com.vicutu.commons.lang.Service;

public class DictionaryService extends Service implements IServiceable
{
	private Cache cache;

	private DictionaryOperator dictionaryOperator;

	public void setDictionaryOperator(DictionaryOperator dictionaryOperator)
	{
		this.dictionaryOperator = dictionaryOperator;
	}

	public void setCache(Cache cache)
	{
		this.cache = cache;
	}

	protected void doStart() throws Throwable
	{
		this.updateAll();
	}

	protected void doStop() throws Throwable
	{
		this.removeAll();
	}

	public void update(Object parameter)
	{
		this.fill(dictionaryOperator.getDictionarys(parameter));
	}

	public void updateAll()
	{
		this.removeAll();
		this.fill(dictionaryOperator.getDictionarys());
	}

	public void removeAll()
	{
		cache.removeAll();
	}

	public void remove(String key)
	{
		cache.remove(key);
	}

	@SuppressWarnings("unchecked")
	public List<DictionaryBean> get(String key)
	{
		Element element = cache.get(key);
		return element != null ? (List<DictionaryBean>) element.getValue() : null;
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<DictionaryBean>> getAll()
	{
		Map<String, List<DictionaryBean>> all = new HashMap<String, List<DictionaryBean>>();

		List<String> keys = cache.getKeysWithExpiryCheck();
		for (int i = 0; i < keys.size(); i++)
		{
			String key = keys.get(i);
			all.put(key, (List<DictionaryBean>) cache.get(key).getValue());
		}
		return all;
	}

	private void fill(Map<String, List<DictionaryBean>> dictionaryMap)
	{
		Set<Entry<String, List<DictionaryBean>>> entrySet = dictionaryMap.entrySet();
		Iterator<Entry<String, List<DictionaryBean>>> it = entrySet.iterator();
		while (it.hasNext())
		{
			Entry<String, List<DictionaryBean>> entry = it.next();
			String key = entry.getKey();
			List<DictionaryBean> dictionaryBeans = entry.getValue();
			if (dictionaryBeans != null)
			{
				Element element = new Element(key, dictionaryBeans);
				cache.put(element);
			}
			else
			{
				cache.remove(key);
			}
		}
	}
}
