package com.mindstatus.component.employee;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mindstatus.bean.vo.MsEmployee;
import com.mindstatus.bo.IMsEmployeeBo;
import com.mindstatus.common.ApplicationConst;
import com.mindstatus.phy.PhysiologyCycleUtils;
import com.vicutu.cache.CacheManager;

public class PrepareEditAction extends Action
{

	protected CacheManager cacheManager;

	protected IMsEmployeeBo msEmployeeBo;

	public void setCacheManager(CacheManager cacheManager)
	{
		this.cacheManager = cacheManager;
	}

	public void setMsEmployeeBo(IMsEmployeeBo msEmployeeBo)
	{
		this.msEmployeeBo = msEmployeeBo;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaActionForm dynaForm = (DynaActionForm) form;
		String editId = request.getParameter("editId");
		MsEmployee msEmployee = null;
		if (editId == null)
		{
			msEmployee = msEmployeeBo.findById((Integer) dynaForm.get("id"));
		}
		else
		{
			msEmployee = msEmployeeBo.findById(Integer.valueOf(editId));
		}

		if(msEmployee.getBirthday()!=null)
		{
			msEmployee.setAge(Integer.valueOf(PhysiologyCycleUtils.computeAgeByBirthday(msEmployee.getBirthday())));
		}
		
		Map<?, ?> propertyMap = PropertyUtils.describe(msEmployee);
		
		PropertyUtils.copyProperties(dynaForm, propertyMap);

		request.setAttribute("sexOption", cacheManager.get("optionListCache").get("sex"));
		request.setAttribute("eduQualificationOption", cacheManager.get("optionListCache").get("edu_qualification"));
		request.setAttribute("politicalAppearanceOption", cacheManager.get("optionListCache").get("political_appearance"));
		request.setAttribute("categoryOption", cacheManager.get("optionListCache").get("category"));
		request.setAttribute("stationOption", cacheManager.get("optionListCache").get("station"));
		request.setAttribute("kidneyOption", cacheManager.get("optionListCache").get("kidney"));
		request.setAttribute("interestOption", cacheManager.get("optionListCache").get("interest"));
		request.setAttribute("associateRelationOption", cacheManager.get("optionListCache").get("associate_relation"));
		request.setAttribute("familyRelationOption", cacheManager.get("optionListCache").get("family_relation"));
		request.setAttribute("communityOption", cacheManager.get("optionListCache").get("community"));
		request.setAttribute("awardOption", cacheManager.get("optionListCache").get("award"));
		request.setAttribute("studyAttitudeOption", cacheManager.get("optionListCache").get("study_attitude"));
		request.setAttribute("responsibilityOption", cacheManager.get("optionListCache").get("responsibility"));
		request.setAttribute("disciplineOption", cacheManager.get("optionListCache").get("discipline"));
		request.setAttribute("campaignOption", cacheManager.get("optionListCache").get("campaign"));
		request.setAttribute("economyOption", cacheManager.get("optionListCache").get("economy"));
		request.setAttribute("afterHoursBusinessOption", cacheManager.get("optionListCache").get("after_hours_business"));
		request.setAttribute("workAdviceOption", cacheManager.get("optionListCache").get("work_advice"));
		request.setAttribute("moralityOption", cacheManager.get("optionListCache").get("morality"));
		request.setAttribute("securityOption", cacheManager.get("optionListCache").get("security"));
		request.setAttribute("serviceAttitudeOption", cacheManager.get("optionListCache").get("service_attitude"));
		request.setAttribute("skillOption", cacheManager.get("optionListCache").get("skill"));
		request.setAttribute("performanceOption", cacheManager.get("optionListCache").get("performance"));
		request.setAttribute("modelEffectOption", cacheManager.get("optionListCache").get("model_effect"));
		request.setAttribute("enlightenOption", cacheManager.get("optionListCache").get("enlighten"));
		request.setAttribute("mindPatternOption", cacheManager.get("optionListCache").get("mind_pattern"));
		request.setAttribute("jobResultOption", cacheManager.get("optionListCache").get("job_result"));
		
		request.setAttribute("promiseOption", cacheManager.get("optionListCache").get("promise"));
		request.setAttribute("ruleOption", cacheManager.get("optionListCache").get("rule"));
		request.setAttribute("honestyOption", cacheManager.get("optionListCache").get("honesty"));
		request.setAttribute("faithOption", cacheManager.get("optionListCache").get("faith"));
		request.setAttribute("dutyOption", cacheManager.get("optionListCache").get("duty"));
		
		request.setAttribute("technologyOption", cacheManager.get("optionListCache").get("technology"));
		request.setAttribute("managementOption", cacheManager.get("optionListCache").get("management"));
		request.setAttribute("artOption", cacheManager.get("optionListCache").get("art"));
		request.setAttribute("sportsOption", cacheManager.get("optionListCache").get("sports"));

		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}

}
