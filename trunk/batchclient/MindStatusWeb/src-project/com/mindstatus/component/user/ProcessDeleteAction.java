package com.mindstatus.component.user;

import java.io.BufferedReader;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mindstatus.bean.vo.MsUser;
import com.mindstatus.bo.IMsUserBo;
import com.mindstatus.common.ApplicationConst;

public class ProcessDeleteAction extends Action {
	protected IMsUserBo msUserBo;

	public void setMsUserBo(IMsUserBo msUserBo) {
		this.msUserBo = msUserBo;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Reader reader = request.getReader();
		BufferedReader bufferedReader = new BufferedReader(reader);
		String id = bufferedReader.readLine();
		if (id != null && !"".equals(id)) {
			MsUser msUser = new MsUser();
			msUser.setId(Integer.valueOf(id));
			msUserBo.delete(msUser);
			response.getWriter().write(ApplicationConst.ACTION_FORWARD_SUCCESS);
		}
		return null;
	}

}
