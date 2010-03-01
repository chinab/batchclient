package com.mindstatus.component.dictionary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mindstatus.bean.vo.MsPropList;
import com.mindstatus.bo.IOptionBo;
import com.mindstatus.common.ApplicationConst;

public class PrepareEditAction extends Action {

	protected IOptionBo optionBo;

	public void setOptionBo(IOptionBo optionBo) {
		this.optionBo = optionBo;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dynaForm = (DynaActionForm) form;
		String propTypeId = request.getParameter("propTypeId");
		Integer propTypeIdInteger=null;
		List<MsPropList> propList=null;
		if(propTypeId==null){
			propTypeIdInteger=(Integer)dynaForm.get("propTypeId");
			propList=optionBo.findPropListByTypeId(propTypeIdInteger);
		}else{
			propTypeIdInteger=Integer.valueOf(propTypeId);
			propList=optionBo.findPropListByTypeId(propTypeIdInteger);
			
		}
		dynaForm.initialize(mapping);
		dynaForm.set("propTypeId", propTypeIdInteger);
		request.setAttribute("msPropList", propList);
		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}

}
