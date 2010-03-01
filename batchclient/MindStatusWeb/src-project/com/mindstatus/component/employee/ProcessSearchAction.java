package com.mindstatus.component.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mindstatus.bo.IMsEmployeeBo;
import com.mindstatus.common.ApplicationConst;
import com.vicutu.cache.CacheManager;
import com.vicutu.op.query.Pagination;
import com.vicutu.op.query.QueryTemplate;

public class ProcessSearchAction extends Action
{
	protected IMsEmployeeBo msEmployeeBo;

	protected CacheManager cacheManager;

	public void setMsEmployeeBo(IMsEmployeeBo msEmployeeBo)
	{
		this.msEmployeeBo = msEmployeeBo;
	}

	public void setCacheManager(CacheManager cacheManager)
	{
		this.cacheManager = cacheManager;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaActionForm dynaForm = (DynaActionForm) form;
		int pageSize = ApplicationConst.DEFAULT_PAGE_SIZE;
		Integer pageSizeInteger = (Integer) dynaForm.get("pageSize");
		if (pageSizeInteger != null)
		{
			pageSize = pageSizeInteger.intValue();
		}

		int startIndex = 0;
		Integer startIndexInteger = (Integer) dynaForm.get("startIndex");
		if (startIndexInteger != null)
		{
			startIndex = startIndexInteger.intValue();
		}
		QueryTemplate queryTemplate = (QueryTemplate) cacheManager.get("queryTemplateCache").get("MsEmployee");
		queryTemplate.setValues(dynaForm.getMap());

		Pagination msEmployeeListPage = msEmployeeBo.findPageByCondition(queryTemplate, pageSize, startIndex);
		request.setAttribute("msEmployeePage", msEmployeeListPage);
		dynaForm.set("pageSize", pageSize);
		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}
}
