package com.vicutu.download.seek.impl;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.vicutu.download.seek.AbstractSeeker;
import com.vicutu.download.task.AtomicTask;

public class CastPlanetSeeker extends AbstractSeeker
{
	protected static final String IMAGE_PAGE_URL_SUFFIX = "?full=1";

	protected static final String PAGE_ONE_SUFFIX = "?page=1";

	protected static final String ROOT_URL = "http://www.castplanet-member.de/gallery/";

	protected int taskCount;

	public Integer call() throws Exception
	{
		if (taskDescriptor.getUrlTemplate() != null)
		{
			logger.info("get single url");
			seek(taskDescriptor.getUrlTemplate(), null);
		}
		else if (taskDescriptor.getUrls() != null)
		{
			logger.info("get {} urls", taskDescriptor.getUrls().size());
			Iterator<String> it = taskDescriptor.getUrls().iterator();
			while (it.hasNext())
			{
				seek(it.next(), null);
			}
		}
		done = true;
		logger.info("seek over...task count : {}", Integer.valueOf(taskCount));
		return Integer.valueOf(taskCount);
	}

	private boolean filtUrl(String linkUrl0, String linkUrl, String lastLinkUrl, String parentUrl)
	{
		return !linkUrl0.equals(lastLinkUrl) && !linkUrl0.equals(linkUrl) && linkUrl0.toLowerCase().startsWith(parentUrl.toLowerCase()) && !linkUrl0.endsWith("php") && !linkUrl0.endsWith(".wmv") && !linkUrl0.endsWith(".jpg") && !linkUrl0.endsWith(PAGE_ONE_SUFFIX);
	}

	private void buildAtomicTask(String tempStr) throws InterruptedException
	{

		String str2 = StringUtils.substringAfter(tempStr, ROOT_URL);
		String folderName = StringUtils.substringBefore(str2, "/");
		File folder = new File(taskDescriptor.getSavePath(), folderName);
		if (!folder.exists())
		{
			folder.mkdirs();
		}
		StringBuffer buffer = new StringBuffer(ROOT_URL);
		buffer.append("albums/");
		buffer.append(str2);
		buffer.append(".jpg");
		String url = buffer.toString();

		File imageFile = new File(folder, StringUtils.substringAfterLast(url, "/"));

		if (!imageFile.exists())
		{
			AtomicTask atomicTask = new AtomicTask();
			atomicTask.setUrl(url);
			atomicTask.setSavePath(imageFile);
			logger.info("AtomicTask-Url : {}", atomicTask.getUrl());
			taskQueue.put(atomicTask);
			taskCount++;
		}
		else
		{
			if (replaceExistFile)
			{
				logger.info("redownload existing file : {}", imageFile);
				AtomicTask atomicTask = new AtomicTask();
				atomicTask.setUrl(url);
				atomicTask.setSavePath(imageFile);
				logger.info("AtomicTask-Url : {}", atomicTask.getUrl());
				taskQueue.put(atomicTask);
				taskCount++;
			}
			else
			{
				logger.info("ignore existing file : {}", imageFile);
			}
		}

	}

	private void seek(String linkUrl, String lastLinkUrl)
	{
		if (linkUrl == null)
		{
			return;
		}
		logger.info("Current linkUrl : {}", linkUrl);
		logger.info("   Last linkUrl : {}", lastLinkUrl);
		try
		{
			String parentUrl = null;
			if (linkUrl.contains("?"))
			{
				parentUrl = StringUtils.substringBefore(linkUrl, "?");
			}
			else
			{
				parentUrl = linkUrl;
			}
			String htmlStr = this.getHtmlString(linkUrl);
			if (htmlStr != null)
			{
				Parser parser = new Parser();
				parser.setInputHTML(htmlStr);
				NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
				Set<String> filtedUrls = new HashSet<String>();
				Set<String> filtedImages = new HashSet<String>();
				for (int i = 0; i < nla.size(); i++)
				{
					Node node = nla.elementAt(i);

					if (node instanceof LinkTag)
					{
						LinkTag lt = (LinkTag) node;
						String linkUrl0 = lt.getLink();
						if (this.filtUrl(linkUrl0, linkUrl, lastLinkUrl, parentUrl))
						{
							int index = linkUrl0.indexOf(IMAGE_PAGE_URL_SUFFIX);
							if (index > 0)
							{
								String tempStr = linkUrl0.substring(0, index);
								filtedImages.add(tempStr);
								this.buildAtomicTask(tempStr);
							}
							else
							{
								filtedUrls.add(linkUrl0);
							}
						}
					}
				}
				logger.info("current task queue : {}", Integer.valueOf(taskQueue.size()));
				filtedUrls.removeAll(filtedImages);
				Iterator<String> it = filtedUrls.iterator();
				while (it.hasNext())
				{
					this.seek(it.next(), linkUrl);
				}
			}
		}
		catch (ParserException e)
		{
			logger.error("occur error when parsing html", e);
		}
		catch (InterruptedException e)
		{
			logger.error("occur error when building AtomicTask", e);
		}
	}
}