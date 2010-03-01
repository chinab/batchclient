package com.vicutu.download.seek.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.vicutu.download.seek.AbstractSeeker;
import com.vicutu.download.task.AtomicTask;

public class OrientalCastGirlsSeeker extends AbstractSeeker
{
	protected static final String ROOT_URL = "http://www.orientalcastgirls.com/Member/";

	private int taskCount;

	public Integer call() throws Exception
	{
		seek(taskDescriptor.getUrlTemplate());
		done = true;
		logger.info("seek over...task count : {}", Integer.valueOf(taskCount));
		return Integer.valueOf(taskCount);
	}

	private void seek(String baseUrl)
	{
		List<String> indexList = this.getAllIndexes(baseUrl);
		for (int i = 0; i < indexList.size(); i++)
		{
			String linkUrl = indexList.get(i);

			String imgae1htmlUrl = linkUrl + "/imagepages/image1.html";
			String imageBaseUrl = linkUrl + "/images/";
			File savePath = new File(taskDescriptor.getSavePath(), StringUtils.replace(StringUtils.substringAfterLast(linkUrl, ROOT_URL), "%20", "_"));
			if (!savePath.exists())
			{
				savePath.mkdirs();
			}
			try
			{
				assembleTask(imgae1htmlUrl, imageBaseUrl, savePath);
			}
			catch (Exception e)
			{
				logger.error("occur error when assembling task", e);
			}
		}
	}

	private void assembleTask(String imgae1htmlUrl, String imageBaseUrl, File savePath) throws Exception
	{
		String htmlStr = this.getHtmlString(imgae1htmlUrl);

		if (htmlStr != null)
		{
			Parser parser = new Parser();

			parser.setInputHTML(htmlStr);
			NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("option"));
			for (int i = 0; i < nla.size(); i++)
			{
				Node node = nla.elementAt(i);
				if (node instanceof OptionTag)
				{
					OptionTag ot = (OptionTag) node;
					String optionText = ot.getOptionText();
					String imageFileName = StringUtils.substringAfter(optionText, " ");
					String imageUrl = imageBaseUrl + imageFileName;
					File imageFile = new File(savePath, imageFileName);
					if (!imageFile.exists())
					{
						this.addTask(imageUrl, imageFile);
					}
					else
					{
						if (replaceExistFile)
						{
							logger.info("redownload existing file : {}", imageFile);
							this.addTask(imageUrl, imageFile);
						}
						else
						{
							logger.info("ignore existing file : {}", imageFile);
						}
					}
				}
			}
		}
	}

	private void addTask(String imageUrl, File imageFile) throws Exception
	{
		AtomicTask atomicTask = new AtomicTask();
		if (StringUtils.contains(imageUrl, ' '))
		{
			imageUrl = StringUtils.replace(imageUrl, " ", StringEscapeUtils.escapeJava("%20"));
		}
		atomicTask.setUrl(imageUrl);
		atomicTask.setSavePath(imageFile);
		taskQueue.put(atomicTask);
		taskCount++;
		logger.info("AtomicTask-Url : {}", imageUrl);
		logger.info("current task queue : {}", Integer.valueOf(taskQueue.size()));
	}

	private List<String> getAllIndexes(String baseUrl)
	{
		String htmlStr = this.getHtmlString(baseUrl);
		List<String> indexList = new ArrayList<String>();
		if (htmlStr != null)
		{
			Parser parser = new Parser();
			try
			{
				parser.setInputHTML(htmlStr);
				NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
				for (int i = 0; i < nla.size(); i++)
				{
					Node node = nla.elementAt(i);
					if (node instanceof LinkTag)
					{
						LinkTag lt = (LinkTag) node;
						String linkUrl0 = lt.getLink();

						if (linkUrl0.startsWith(ROOT_URL))
						{
							indexList.add(linkUrl0);
						}
					}
				}
			}
			catch (ParserException e)
			{
				logger.error("occur error when parsing html", e);
			}
		}
		return indexList;
	}
}