package com.mindstatus.component.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vicutu.cache.CacheManager;

public class PrepareSearchAction extends Action
{

	protected CacheManager cacheManager;

	public void setCacheManager(CacheManager cacheManager)
	{
		this.cacheManager = cacheManager;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
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
		
		request.setAttribute("pageSizeOption", cacheManager.get("optionListCache").get("page_size"));

		return mapping.findForward("success");
	}
}
