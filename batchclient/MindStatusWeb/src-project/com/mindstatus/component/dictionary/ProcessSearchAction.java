package com.mindstatus.component.dictionary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mindstatus.bean.vo.MsPropType;
import com.mindstatus.bo.IOptionBo;
import com.mindstatus.common.ApplicationConst;

public class ProcessSearchAction extends Action {
	
	protected IOptionBo optionBo;
	
	public void setOptionBo(IOptionBo optionBo) {
		this.optionBo = optionBo;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<MsPropType> msPropTypeList=optionBo.findAllPropType();
		request.setAttribute("msPropTypeList", msPropTypeList);
		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}

}
