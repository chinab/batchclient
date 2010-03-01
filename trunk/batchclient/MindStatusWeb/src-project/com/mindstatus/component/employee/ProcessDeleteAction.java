package com.mindstatus.component.employee;

import java.io.BufferedReader;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mindstatus.bean.vo.MsEmployee;
import com.mindstatus.bo.IMsEmployeeBo;
import com.mindstatus.common.ApplicationConst;

public class ProcessDeleteAction extends Action {

	protected IMsEmployeeBo msEmployeeBo;	
	
	public void setMsEmployeeBo(IMsEmployeeBo msEmployeeBo) {
		this.msEmployeeBo = msEmployeeBo;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Reader reader=request.getReader();
		BufferedReader bufferedReader=new BufferedReader(reader);
		String id=bufferedReader.readLine();
		if(id!=null && !"".equals(id)){
			MsEmployee msEmployee=new MsEmployee();
			msEmployee.setId(Integer.valueOf(id));
			msEmployeeBo.delete(msEmployee);
			response.getWriter().write(ApplicationConst.ACTION_FORWARD_SUCCESS);
		}
		return null;
	}
}
