package com.mindstatus.bean.vo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * MsPropType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MsPropType implements java.io.Serializable
{

	private static final long serialVersionUID = 795102051897470437L;

	private Integer propTypeId;

	private String propTypeName;

	private String propTypeLabel;

	private String memo;

	// Constructors

	/** default constructor */
	public MsPropType()
	{
	}

	/** minimal constructor */
	public MsPropType(Integer propTypeId)
	{
		this.propTypeId = propTypeId;
	}

	// Property accessors

	public Integer getPropTypeId()
	{
		return this.propTypeId;
	}

	public void setPropTypeId(Integer propTypeId)
	{
		this.propTypeId = propTypeId;
	}

	public String getPropTypeName()
	{
		return this.propTypeName;
	}

	public void setPropTypeName(String propTypeName)
	{
		this.propTypeName = propTypeName;
	}

	public String getPropTypeLabel()
	{
		return this.propTypeLabel;
	}

	public void setPropTypeLabel(String propTypeLabel)
	{
		this.propTypeLabel = propTypeLabel;
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