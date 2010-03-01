package com.vicutu.services.dictionary;

import java.util.List;
import java.util.Map;

public interface DictionaryOperator
{
	Map<String, List<DictionaryBean>> getDictionarys();

	Map<String, List<DictionaryBean>> getDictionarys(Object parameter);
}