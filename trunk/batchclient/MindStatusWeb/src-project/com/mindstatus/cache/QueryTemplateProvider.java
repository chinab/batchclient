package com.mindstatus.cache;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.vicutu.cache.Cache;
import com.vicutu.cache.CacheDataProvider;
import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.lang.PathUtils;
import com.vicutu.commons.xml.XmlUtils;
import com.vicutu.op.query.QueryTemplate;
import com.vicutu.op.query.impl.QueryTemplateImpl;

public class QueryTemplateProvider implements CacheDataProvider
{
	private static final String FILE_PATTERN = "[\\S]*.xml";

	private static final String QUERY_PATH = "resource/query";

	protected static Logger logger = Logger.getLogger(QueryTemplateProvider.class);

	public void clear(Cache cache)
	{
		cache.removeAll();
	}

	public void fill(Cache cache)
	{
		String queryPath = PathUtils.getSystemPath() + QUERY_PATH;
		File queryDir = new File(queryPath);
		File[] queryFiles = FileUtils.filterFiles(queryDir, FILE_PATTERN);

		try
		{
			for (int i = 0; i < queryFiles.length; i++)
			{
				logger.info("Load QueryTemplate File:" + queryFiles[i].getAbsolutePath());
				String fileName = queryFiles[i].getName().toLowerCase();
				String defaultQueryName = fileName.substring(0, fileName.indexOf(".xml"));
				Document doc = XmlUtils.createDocument(queryFiles[i], false);
				QueryTemplate qt = new QueryTemplateImpl(doc.getRootElement(), defaultQueryName);
				cache.put(qt.getName(), qt);
			}
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
	}

	public void update(Cache cache, Object parameter)
	{
	}
}
