package com.mindstatus.controller.employee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.mindstatus.business.IEmployee;
import com.mindstatus.module.MsEmployee;
import com.mindstatus.module.query.MsEmployeeQuery;
import com.vicutu.persistence.query.Pagination;

public class EmployeeController extends MultiActionController
{
	protected IEmployee employee;

	public void setEmployee(IEmployee employee)
	{
		this.employee = employee;
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(df, true));
	}

	public ModelAndView showAdd(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("employee/add");
	}

	public ModelAndView showEdit(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("employee/edit");
	}

	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, MsEmployee msEmployee)
	{
		
		Map<String, Object> model = new HashMap<String, Object>();
		try
		{
			employee.saveOrUpdate(msEmployee);
			model.put("success", Boolean.TRUE);
			model.put("message", "success");
		}
		catch (Exception e)
		{
			model.put("success", Boolean.FALSE);
			model.put("message", "fail");
			e.printStackTrace();
		}
		
		return new ModelAndView("jsonView", model);
	}

	public ModelAndView query(HttpServletRequest request, HttpServletResponse response, MsEmployeeQuery msEmployeeQuery)
	{
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.valueOf(startStr).intValue();
		int limit = Integer.valueOf(limitStr).intValue();
		Map<String, Object> model = new HashMap<String, Object>();
		Pagination pagination;
		try
		{
			pagination = employee.queryByCondition(msEmployeeQuery, start, limit);
			
			model.put("total", Integer.valueOf(pagination.getTotal()));
			model.put("rows", pagination.getItems());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("jsonView", model);
	}
}
