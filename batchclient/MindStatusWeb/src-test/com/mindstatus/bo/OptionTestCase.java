package com.mindstatus.bo;

import java.util.List;
import java.util.Map;

import com.mindstatus.bean.vo.MsPropList;
import com.vicutu.test.SpringBaseTestCase;

public class OptionTestCase extends SpringBaseTestCase {
	public void test_getMappedPropList(){
		IOptionBo iOption=(IOptionBo)getBean("optionBoProxy");
		Map<String, List<MsPropList>> propListMap=iOption.getMappedPropList();
		logger.println(propListMap);
	}
}
