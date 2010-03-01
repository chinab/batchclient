package com.vicutu.download.task;

import java.io.File;

public class AtomicTask
{
	private String url;
	
	private File savePath;

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public File getSavePath()
	{
		return savePath;
	}

	public void setSavePath(File savePath)
	{
		this.savePath = savePath;
	}
}