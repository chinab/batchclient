package com.mindstatus.bo;

import java.util.List;
import java.util.Map;

import com.mindstatus.bean.vo.MsPropList;
import com.mindstatus.bean.vo.MsPropType;

public interface IOptionBo {
	Map<String,List<MsPropList>> getMappedPropList();
	
	List<MsPropType> findAllPropType();
	
	List<MsPropList> findPropListByTypeId(Integer propTypeId);
	
	List<MsPropList> findPropListByTypeName(String propTypeName);
	
	void saveOrUpdateMsPropList(MsPropList msPropList);
	
	void saveOrUpdateMsPropType(MsPropType msPropType);
	
	MsPropType findPropTypeById(Integer propTypeId);
}
