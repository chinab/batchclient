package com.mindstatus.bean.vo;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * MsUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MsUser implements java.io.Serializable
{

	private static final long serialVersionUID = -485998319443006902L;

	private Integer id;

	private String userName;

	private String realName;

	private Integer sex;

	private String password;

	private Date regTime;

	private Integer loginTimes;

	// Constructors

	/** default constructor */
	public MsUser()
	{
	}

	/** minimal constructor */
	public MsUser(Integer id)
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

	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Integer getSex()
	{
		return this.sex;
	}

	public void setSex(Integer sex)
	{
		this.sex = sex;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Date getRegTime()
	{
		return this.regTime;
	}

	public void setRegTime(Date regTime)
	{
		this.regTime = regTime;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public Integer getLoginTimes()
	{
		return this.loginTimes;
	}

	public void setLoginTimes(Integer loginTimes)
	{
		this.loginTimes = loginTimes;
	}

	public String toString()
	{
		return ReflectionToStringBuilder.toString(this);
	}
}