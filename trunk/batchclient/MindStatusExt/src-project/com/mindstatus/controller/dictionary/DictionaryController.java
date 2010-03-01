package com.mindstatus.controller.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vicutu.services.dictionary.DictionaryBean;
import com.vicutu.services.dictionary.DictionaryService;

public class DictionaryController extends MultiActionController
{
	protected DictionaryService dictionaryService;

	public void setDictionaryService(DictionaryService dictionaryService)
	{
		this.dictionaryService = dictionaryService;
	}

	public ModelAndView getDictionaryByTypeName(HttpServletRequest request, HttpServletResponse response)
	{
		String propTypeName = request.getParameter("prop_type");
		List<DictionaryBean> msPropList = dictionaryService.get(propTypeName);

		Map<String, List<DictionaryBean>> model = new HashMap<String, List<DictionaryBean>>();
		model.put("prop_list", msPropList);
		return new ModelAndView("jsonView", model);
	}
}
