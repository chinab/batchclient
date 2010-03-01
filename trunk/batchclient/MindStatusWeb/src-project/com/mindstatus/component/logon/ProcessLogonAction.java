package com.mindstatus.component.logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mindstatus.bean.vo.MsUser;
import com.mindstatus.bo.IMsUserBo;
import com.mindstatus.common.ApplicationConst;

public class ProcessLogonAction extends Action {
	IMsUserBo msUserBo;

	public void setMsUserBo(IMsUserBo msUserBo) {
		this.msUserBo = msUserBo;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dynaForm = (DynaActionForm) form;
		MsUser msUser=new MsUser();
		PropertyUtils.copyProperties(msUser, dynaForm.getMap());
		int errcode=msUserBo.logon(msUser);
		if(errcode==IMsUserBo.LOGON_CODE_SUCCESS){
			msUser.setPassword(dynaForm.get("password").toString());
			HttpSession session=request.getSession();
			session.setAttribute(ApplicationConst.CURRENT_USER, msUser);
			return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
		}else{
			dynaForm.set("errorCode", ""+errcode);
			return mapping.getInputForward();
		}
		
		
	}
}
