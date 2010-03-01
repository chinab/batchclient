package com.mindstatus.bean.vo;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * MsEmployee entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MsEmployee implements java.io.Serializable
{

	private static final long serialVersionUID = 4087679737997094325L;

	private Integer id;

	private String name;

	private Integer sex;

	private Integer age;

	private Date birthday;

	private Integer eduQualification;

	private Integer politicalAppearance;

	private Integer category;

	private Integer station;

	private Integer kidney;

	private Integer interest;

	private Integer associateRelation;

	private Integer familyRelation;

	private Integer community;

	private Integer award;

	private Integer studyAttitude;

	private Integer responsibility;

	private Integer campaign;

	private Integer economy;

	private Integer afterHoursBusiness;

	private Integer morality;

	private Integer security;

	private Integer serviceAttitude;

	private Integer skill;

	private Integer performance;

	private Integer modelEffect;

	private Integer enlighten;

	private Integer mindPattern;

	private Integer jobResult;

	private String memo;

	//new requirement
	private Integer promise;

	private Integer rule;

	private Integer honesty;

	private Integer faith;

	private Integer duty;

	private Integer technology;

	private Integer management;

	private Integer art;

	private Integer sports;

	public Integer getTechnology()
	{
		return technology;
	}

	public void setTechnology(Integer technology)
	{
		this.technology = technology;
	}

	public Integer getManagement()
	{
		return management;
	}

	public void setManagement(Integer management)
	{
		this.management = management;
	}

	public Integer getArt()
	{
		return art;
	}

	public void setArt(Integer art)
	{
		this.art = art;
	}

	public Integer getSports()
	{
		return sports;
	}

	public void setSports(Integer sports)
	{
		this.sports = sports;
	}

	public Integer getPromise()
	{
		return promise;
	}

	public void setPromise(Integer promise)
	{
		this.promise = promise;
	}

	public Integer getRule()
	{
		return rule;
	}

	public void setRule(Integer rule)
	{
		this.rule = rule;
	}

	public Integer getHonesty()
	{
		return honesty;
	}

	public void setHonesty(Integer honesty)
	{
		this.honesty = honesty;
	}

	public Integer getFaith()
	{
		return faith;
	}

	public void setFaith(Integer faith)
	{
		this.faith = faith;
	}

	public Integer getDuty()
	{
		return duty;
	}

	public void setDuty(Integer duty)
	{
		this.duty = duty;
	}

	// Constructors

	/** default constructor */
	public MsEmployee()
	{
	}

	/** minimal constructor */
	public MsEmployee(Integer id)
	{
		this.id = id;
	}

	// Property accessors

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getSex()
	{
		return this.sex;
	}

	public void setSex(Integer sex)
	{
		this.sex = sex;
	}

	public Integer getAge()
	{
		return this.age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public Date getBirthday()
	{
		return this.birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public Integer getEduQualification()
	{
		return this.eduQualification;
	}

	public void setEduQualification(Integer eduQualification)
	{
		this.eduQualification = eduQualification;
	}

	public Integer getPoliticalAppearance()
	{
		return this.politicalAppearance;
	}

	public void setPoliticalAppearance(Integer politicalAppearance)
	{
		this.politicalAppearance = politicalAppearance;
	}

	public Integer getCategory()
	{
		return this.category;
	}

	public void setCategory(Integer category)
	{
		this.category = category;
	}

	public Integer getStation()
	{
		return this.station;
	}

	public void setStation(Integer station)
	{
		this.station = station;
	}

	public Integer getKidney()
	{
		return this.kidney;
	}

	public void setKidney(Integer kidney)
	{
		this.kidney = kidney;
	}

	public Integer getInterest()
	{
		return this.interest;
	}

	public void setInterest(Integer interest)
	{
		this.interest = interest;
	}

	public Integer getAssociateRelation()
	{
		return this.associateRelation;
	}

	public void setAssociateRelation(Integer associateRelation)
	{
		this.associateRelation = associateRelation;
	}

	public Integer getFamilyRelation()
	{
		return this.familyRelation;
	}

	public void setFamilyRelation(Integer familyRelation)
	{
		this.familyRelation = familyRelation;
	}

	public Integer getCommunity()
	{
		return this.community;
	}

	public void setCommunity(Integer community)
	{
		this.community = community;
	}

	public Integer getAward()
	{
		return this.award;
	}

	public void setAward(Integer award)
	{
		this.award = award;
	}

	public Integer getStudyAttitude()
	{
		return this.studyAttitude;
	}

	public void setStudyAttitude(Integer studyAttitude)
	{
		this.studyAttitude = studyAttitude;
	}

	public Integer getResponsibility()
	{
		return this.responsibility;
	}

	public void setResponsibility(Integer responsibility)
	{
		this.responsibility = responsibility;
	}

	public Integer getCampaign()
	{
		return this.campaign;
	}

	public void setCampaign(Integer campaign)
	{
		this.campaign = campaign;
	}

	public Integer getEconomy()
	{
		return this.economy;
	}

	public void setEconomy(Integer economy)
	{
		this.economy = economy;
	}

	public Integer getAfterHoursBusiness()
	{
		return this.afterHoursBusiness;
	}

	public void setAfterHoursBusiness(Integer afterHoursBusiness)
	{
		this.afterHoursBusiness = afterHoursBusiness;
	}

	public Integer getMorality()
	{
		return this.morality;
	}

	public void setMorality(Integer morality)
	{
		this.morality = morality;
	}

	public Integer getSecurity()
	{
		return this.security;
	}

	public void setSecurity(Integer security)
	{
		this.security = security;
	}

	public Integer getServiceAttitude()
	{
		return this.serviceAttitude;
	}

	public void setServiceAttitude(Integer serviceAttitude)
	{
		this.serviceAttitude = serviceAttitude;
	}

	public Integer getSkill()
	{
		return this.skill;
	}

	public void setSkill(Integer skill)
	{
		this.skill = skill;
	}

	public Integer getPerformance()
	{
		return this.performance;
	}

	public void setPerformance(Integer performance)
	{
		this.performance = performance;
	}

	public Integer getModelEffect()
	{
		return this.modelEffect;
	}

	public void setModelEffect(Integer modelEffect)
	{
		this.modelEffect = modelEffect;
	}

	public Integer getEnlighten()
	{
		return this.enlighten;
	}

	public void setEnlighten(Integer enlighten)
	{
		this.enlighten = enlighten;
	}

	public Integer getMindPattern()
	{
		return this.mindPattern;
	}

	public void setMindPattern(Integer mindPattern)
	{
		this.mindPattern = mindPattern;
	}

	public Integer getJobResult()
	{
		return this.jobResult;
	}

	public void setJobResult(Integer jobResult)
	{
		this.jobResult = jobResult;
	}

	public String toString()
	{
		return ReflectionToStringBuilder.toString(this);
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}
}