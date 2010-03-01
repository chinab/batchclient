package com.mindstatus.component.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mindstatus.bean.vo.MsEmployee;
import com.mindstatus.bo.IMsEmployeeBo;
import com.mindstatus.common.ApplicationConst;

public class ProcessEditAction extends Action
{

	protected IMsEmployeeBo msEmployeeBo;

	public void setMsEmployeeBo(IMsEmployeeBo msEmployeeBo)
	{
		this.msEmployeeBo = msEmployeeBo;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MsEmployee msEmployee = new MsEmployee();
		DynaActionForm dynaForm = (DynaActionForm) form;
		PropertyUtils.copyProperties(msEmployee, dynaForm.getMap());
		msEmployeeBo.saveOrUpdate(msEmployee);
		request.setAttribute(ApplicationConst.ACTION_FORWARD_SUCCESS, ApplicationConst.ACTION_FORWARD_SUCCESS);
		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}
}
