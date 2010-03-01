package com.mindstatus.bean.vo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * MsPropList entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MsPropList implements java.io.Serializable
{

	private static final long serialVersionUID = 1675236065672623624L;

	private Integer id;

	private Integer propTypeId;

	private Integer propListId;

	private String propListName;

	private String memo;

	// Constructors

	/** default constructor */
	public MsPropList()
	{
	}

	/** minimal constructor */
	public MsPropList(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getPropTypeId()
	{
		return this.propTypeId;
	}

	public void setPropTypeId(Integer propTypeId)
	{
		this.propTypeId = propTypeId;
	}

	public Integer getPropListId()
	{
		return this.propListId;
	}

	public void setPropListId(Integer propListId)
	{
		this.propListId = propListId;
	}

	public String getPropListName()
	{
		return this.propListName;
	}

	public void setPropListName(String propListName)
	{
		this.propListName = propListName;
	}

	public String getMemo()
	{
		return this.memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public String toString()
	{
		return ReflectionToStringBuilder.toString(this);
	}

}