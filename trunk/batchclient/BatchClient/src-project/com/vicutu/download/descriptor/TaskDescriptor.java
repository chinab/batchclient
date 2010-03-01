package com.vicutu.download.descriptor;

import java.util.List;


public class TaskDescriptor
{
	private String name;

	private int rangeFrom = -1;

	private int rangeTo = -1;

	private int patternLength = 3;

	private String savePath;

	private String urlTemplate;
	
	private List<String> urls;

	public List<String> getUrls()
	{
		return urls;
	}

	public void setUrls(List<String> urls)
	{
		this.urls = urls;
	}

	private String excludes;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getRangeFrom()
	{
		return rangeFrom;
	}

	public void setRangeFrom(int rangeFrom)
	{
		this.rangeFrom = rangeFrom;
	}

	public int getRangeTo()
	{
		return rangeTo;
	}

	public void setRangeTo(int rangeTo)
	{
		this.rangeTo = rangeTo;
	}

	public int getPatternLength()
	{
		return patternLength;
	}

	public void setPatternLength(int patternLength)
	{
		this.patternLength = patternLength;
	}

	public String getSavePath()
	{
		return savePath;
	}

	public void setSavePath(String savePath)
	{
		this.savePath = savePath;
	}

	public String getUrlTemplate()
	{
		return urlTemplate;
	}

	public void setUrlTemplate(String urlTemplate)
	{
		this.urlTemplate = urlTemplate;
	}

	public String getExcludes()
	{
		return excludes;
	}

	public void setExcludes(String excludes)
	{
		this.excludes = excludes;
	}
}