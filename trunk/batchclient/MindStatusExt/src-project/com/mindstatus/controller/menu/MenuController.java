package com.mindstatus.controller.menu;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.mindstatus.business.IMenu;
import com.mindstatus.module.MsMenu;

public class MenuController extends MultiActionController
{
	protected IMenu menu;

	public void setMenu(IMenu menu)
	{
		this.menu = menu;
	}

	public ModelAndView getTreeNodeById(HttpServletRequest request, HttpServletResponse response)
	{
		String node = request.getParameter("node");
		//调用业务
		List<MsMenu> list = menu.findTreeNodeById(Integer.valueOf(node));

		JSONArray jsonObject = JSONArray.fromObject(list);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try
		{
			response.getWriter().write(jsonObject.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
