package com.mindstatus.component.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mindstatus.bean.vo.MsUser;
import com.mindstatus.bo.IMsUserBo;
import com.mindstatus.common.ApplicationConst;

public class ProcessInsertAction extends Action{
protected IMsUserBo msUserBo;	
	
	public void setMsUserBo(IMsUserBo msUserBo) {
		this.msUserBo = msUserBo;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MsUser msUser=new MsUser();
		DynaActionForm dynaForm=(DynaActionForm)form;
		PropertyUtils.copyProperties(msUser, dynaForm.getMap());

		int result=msUserBo.regist(msUser);
		if(result==IMsUserBo.REGIST_CODE_SUCCESS){
			request.setAttribute(ApplicationConst.ACTION_FORWARD_SUCCESS, ApplicationConst.ACTION_FORWARD_SUCCESS);
		}else{
			request.setAttribute(ApplicationConst.ACTION_FORWARD_FAIL, ApplicationConst.ACTION_FORWARD_FAIL);
		}
		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}

}
