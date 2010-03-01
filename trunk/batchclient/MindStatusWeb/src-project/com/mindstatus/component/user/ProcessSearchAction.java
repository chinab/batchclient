package com.mindstatus.component.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mindstatus.bo.IMsUserBo;
import com.mindstatus.common.ApplicationConst;
import com.vicutu.cache.CacheManager;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

public class ProcessSearchAction extends Action {
	protected CacheManager cacheManager;
	protected IMsUserBo msUserBo;

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setMsUserBo(IMsUserBo msUserBo) {
		this.msUserBo = msUserBo;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dynaForm = (DynaActionForm) form;

		int pageSize = ApplicationConst.DEFAULT_PAGE_SIZE;
		Integer pageSizeInteger = (Integer) dynaForm.get("pageSize");
		if (pageSizeInteger != null) {
			pageSize = pageSizeInteger.intValue();
		}

		int startIndex = 0;
		Integer startIndexInteger = (Integer) dynaForm.get("startIndex");
		if (startIndexInteger != null) {
			startIndex = startIndexInteger.intValue();
		}
		QueryTemplate queryTemplate = (QueryTemplate) cacheManager.get(
				"queryTemplateCache").get("MsUser");
		Pagination msUserListPage = msUserBo.findPageByCondition(
				queryTemplate, pageSize, startIndex);

		request.setAttribute("msUserPage", msUserListPage);
		dynaForm.set("pageSize", pageSize);
		request.setAttribute("sexOption", cacheManager.get("optionListCache")
				.get("sex"));
		request.setAttribute("pageSizeOption", cacheManager.get(
		"optionListCache").get("page_size"));
		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}

}
