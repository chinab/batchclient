package com.mindstatus.component.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.vicutu.cache.CacheManager;

public class PrepareInsertAction extends Action {
protected CacheManager cacheManager;
	
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dynaForm=(DynaActionForm)form;
		dynaForm.initialize(mapping);
		request.setAttribute("sexOption", cacheManager.get("optionListCache").get("sex"));
		return mapping.findForward("success");
	}

}
