package com.mindstatus.component.logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mindstatus.common.ApplicationConst;

public class ProcessLogoffAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		session.removeAttribute(ApplicationConst.CURRENT_USER);
		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}

}
