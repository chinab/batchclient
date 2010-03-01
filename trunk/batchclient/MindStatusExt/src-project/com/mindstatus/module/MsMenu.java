package com.mindstatus.module;

/**
 * Primarykeycn entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MsMenu implements java.io.Serializable
{
	private static final long serialVersionUID = -3580923538054305336L;

	private Integer id;

	private String text;

	private Boolean leaf;

	private String linkUrl;

	private Integer parentId;

	private String iconCls;

	public String getIconCls()
	{
		return iconCls;
	}

	public void setIconCls(String iconCls)
	{
		this.iconCls = iconCls;
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	/** default constructor */
	public MsMenu()
	{
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getText()
	{
		return this.text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Boolean getLeaf()
	{
		return leaf;
	}

	public void setLeaf(Boolean leaf)
	{
		this.leaf = leaf;
	}

	public String getLinkUrl()
	{
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl)
	{
		this.linkUrl = linkUrl;
	}
}