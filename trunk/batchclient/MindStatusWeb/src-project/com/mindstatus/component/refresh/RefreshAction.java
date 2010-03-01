package com.mindstatus.component.refresh;

import java.io.BufferedReader;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RefreshAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Reader reader=request.getReader();
		BufferedReader bufferedReader=new BufferedReader(reader);
		String str=bufferedReader.readLine();
		str="Requset from "+request.getRemoteAddr()+" :"+str;
		//System.out.println(str);
		response.getWriter().write(str);
		return null;
	}
}
