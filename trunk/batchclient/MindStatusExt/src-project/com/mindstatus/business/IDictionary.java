package com.mindstatus.business;

import java.util.List;
import java.util.Map;

import com.mindstatus.module.MsPropList;
import com.mindstatus.module.MsPropType;

public interface IDictionary
{
	Map<String, List<MsPropList>> getMappedPropList();

	List<MsPropType> findAllPropType();

	List<MsPropList> findPropListByTypeId(Integer propTypeId);

	List<MsPropList> findPropListByTypeName(String propTypeName);

	void saveOrUpdateMsPropList(MsPropList msPropList);

	void saveOrUpdateMsPropType(MsPropType msPropType);

	MsPropType findPropTypeById(Integer propTypeId);
}
