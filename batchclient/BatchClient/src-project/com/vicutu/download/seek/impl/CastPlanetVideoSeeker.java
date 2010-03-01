package com.vicutu.download.seek.impl;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.vicutu.download.task.AtomicTask;

public class CastPlanetVideoSeeker extends CastPlanetSeeker
{

	private Set<String> visitNode = new TreeSet<String>();

	public Integer call() throws Exception
	{
		seek(taskDescriptor.getUrlTemplate());
		done = true;
		logger.info("seek over...task count : {}", Integer.valueOf(taskCount));
		return Integer.valueOf(taskCount);
	}

	private void seek(String url)
	{
		logger.info("Current linkUrl : {}", url);
		try
		{
			visitNode.add(url);
			String htmlStr = this.getHtmlString(url);
			if (htmlStr != null)
			{
				Parser parser = new Parser();
				parser.setInputHTML(htmlStr);
				NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
				LinkedList<String> filtedUrls = new LinkedList<String>();
				for (int i = 0; i < nla.size(); i++)
				{
					Node node = nla.elementAt(i);

					if (node instanceof LinkTag)
					{
						LinkTag lt = (LinkTag) node;
						String linkUrl0 = lt.getLink();
						if (linkUrl0.endsWith(".wmv"))
						{
							AtomicTask atomicTask = this.buildAtomicTask(linkUrl0);
							if (atomicTask.getSavePath().exists())
							{
								logger.info("File exists : {}", atomicTask.getSavePath().getName());
							}
							else
							{
								logger.info("AtomicTask-Url : " + atomicTask.getUrl());
								taskQueue.put(atomicTask);
								taskCount++;
								logger.info("current task queue : {}", Integer.valueOf(taskQueue.size()));
							}
						}
						else
						{
							if (linkUrl0.startsWith("http://") && (linkUrl0.contains("VIDEOS-") || linkUrl0.contains("VIDEO-")) && !linkUrl0.endsWith(PAGE_ONE_SUFFIX) && !visitNode.contains(linkUrl0))
							{
								if (linkUrl0.contains("?"))
								{
									filtedUrls.addLast(linkUrl0);
								}
								else
								{
									filtedUrls.addFirst(linkUrl0);
								}
							}
						}
					}
				}
				Iterator<String> it = filtedUrls.iterator();
				while (it.hasNext())
				{
					this.seek(it.next());
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

	private AtomicTask buildAtomicTask(String url)
	{
		AtomicTask atomicTask = new AtomicTask();
		atomicTask.setUrl(url);
		atomicTask.setSavePath(new File(taskDescriptor.getSavePath(), StringUtils.substringAfterLast(url, "/")));
		return atomicTask;
	}
}
