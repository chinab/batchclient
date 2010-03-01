package com.mindstatus.component.dictionary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mindstatus.bean.vo.MsPropList;
import com.mindstatus.bo.IOptionBo;
import com.mindstatus.common.ApplicationConst;
import com.vicutu.cache.CacheManager;
import com.vicutu.event.Event;
import com.vicutu.event.EventPublisher;

public class ProcessEditAction extends Action {
	
	protected EventPublisher eventPublisher;
	
	protected IOptionBo optionBo;
	
	protected CacheManager cacheManager;
	
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setOptionBo(IOptionBo optionBo) {
		this.optionBo = optionBo;
	}
	

	public void setEventPublisher(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dynaForm = (DynaActionForm) form;
		
		MsPropList msPropList=new MsPropList();
		PropertyUtils.copyProperties(msPropList, dynaForm.getMap());
		
		optionBo.saveOrUpdateMsPropList(msPropList);
		Event event=new Event(this,"option_list_changed",msPropList.getPropTypeId());
		eventPublisher.publish(event);
		request.setAttribute(ApplicationConst.ACTION_FORWARD_SUCCESS, ApplicationConst.ACTION_FORWARD_SUCCESS);
		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}
}
