package com.vicutu.web.plugin.struts;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.vicutu.bean.BeanConvertUtils;

public class NullableConverterRegistPlugIn implements PlugIn {

	
	public void destroy() {
		BeanConvertUtils.deregister();
	}

	
	public void init(ActionServlet arg0, ModuleConfig arg1)
			throws ServletException {
		BeanConvertUtils.registNullableConverters();
	}
}
