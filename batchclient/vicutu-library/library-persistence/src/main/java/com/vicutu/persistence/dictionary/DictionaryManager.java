package com.vicutu.persistence.dictionary;

import java.util.List;
import java.util.Map;

public interface DictionaryManager {

	public abstract void update(Object parameter);

	public abstract void updateAll();

	public abstract void removeAll();

	public abstract void remove(String key);

	public abstract List<DictionaryBean> get(String key);

	public abstract Map<String, List<DictionaryBean>> getAll();

}