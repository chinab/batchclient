package com.vicutu.persistence.dictionary.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.persistence.dictionary.DictionaryBean;
import com.vicutu.persistence.dictionary.DictionaryManager;
import com.vicutu.persistence.dictionary.DictionaryOperator;

public class DictionaryManagerBean implements DictionaryManager {

	private static Logger logger = LoggerFactory.getLogger(DictionaryManagerBean.class);

	private Cache cache;

	private DictionaryOperator dictionaryOperator;

	public void setDictionaryOperator(DictionaryOperator dictionaryOperator) {
		this.dictionaryOperator = dictionaryOperator;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public void init() {
		logger.info("loading data dictionarys...");
		this.updateAll();
	}

	public void destroy() {
		logger.info("unloading data dictionarys...");
		this.removeAll();
	}

	@Override
	public void update(Object parameter) {
		this.fill(dictionaryOperator.getDictionarys(parameter));
	}

	@Override
	public void updateAll() {
		this.removeAll();
		this.fill(dictionaryOperator.getDictionarys());
	}

	@Override
	public void removeAll() {
		cache.removeAll();
	}

	@Override
	public void remove(String key) {
		cache.remove(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DictionaryBean> get(String key) {
		Element element = cache.get(key);
		return element != null ? (List<DictionaryBean>) element.getValue() : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, List<DictionaryBean>> getAll() {
		Map<String, List<DictionaryBean>> all = new HashMap<String, List<DictionaryBean>>();

		List<String> keys = cache.getKeysWithExpiryCheck();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			all.put(key, (List<DictionaryBean>) cache.get(key).getValue());
		}
		return all;
	}

	private void fill(Map<String, List<DictionaryBean>> dictionaryMap) {
		Set<Entry<String, List<DictionaryBean>>> entrySet = dictionaryMap.entrySet();
		Iterator<Entry<String, List<DictionaryBean>>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<String, List<DictionaryBean>> entry = it.next();
			String key = entry.getKey();
			List<DictionaryBean> dictionaryBeans = entry.getValue();
			if (dictionaryBeans != null) {
				Element element = new Element(key, dictionaryBeans);
				cache.put(element);
			} else {
				cache.remove(key);
			}
		}
	}
}
