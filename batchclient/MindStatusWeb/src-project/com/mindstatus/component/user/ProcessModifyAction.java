package com.mindstatus.component.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mindstatus.bean.vo.MsUser;
import com.mindstatus.bo.IMsUserBo;
import com.mindstatus.common.ApplicationConst;

public class ProcessModifyAction extends Action {
	protected IMsUserBo msUserBo;

	public void setMsUserBo(IMsUserBo msUserBo) {
		this.msUserBo = msUserBo;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		String strOldPwd = dynaForm.get("oldPwd").toString();
		String strNewPwd = dynaForm.get("password").toString();
		MsUser currentUser = (MsUser) request.getSession().getAttribute(
				ApplicationConst.CURRENT_USER);
		if (currentUser != null) {
			if (!currentUser.getPassword().equals(strOldPwd)) {
				request.setAttribute(ApplicationConst.ACTION_FORWARD_FAIL,
						ApplicationConst.ACTION_FORWARD_FAIL);
				return mapping
						.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
			} else {
				currentUser.setPassword(strNewPwd);
				msUserBo.modify(currentUser);
				currentUser.setPassword(strNewPwd);
				request.setAttribute(ApplicationConst.ACTION_FORWARD_SUCCESS,
						ApplicationConst.ACTION_FORWARD_SUCCESS);
				return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
			}
		}	
		request.setAttribute(ApplicationConst.ACTION_FORWARD_SUCCESS,
				ApplicationConst.ACTION_FORWARD_SUCCESS);
		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}
}
