package com.vicutu.services.query;

import java.io.File;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.dom4j.Document;

import com.vicutu.commons.config.Application;
import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.lang.IServiceable;
import com.vicutu.commons.lang.Service;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.commons.xml.XmlUtils;
import com.vicutu.persistence.query.QueryTemplate;
import com.vicutu.persistence.query.QueryTemplateManager;
import com.vicutu.persistence.query.impl.QueryTemplateImpl;

public class QueryTemplateService extends Service implements QueryTemplateManager, IServiceable
{
	private static Logger logger = LoggerFactory.getLogger(QueryTemplateService.class);

	private static final String FILE_PATTERN = "[\\S]*.xml";

	private static final String QUERY_PATH = "resource/query";

	private Cache cache;

	public void setCache(Cache cache)
	{
		this.cache = cache;
	}

	protected void doStart() throws Throwable
	{
		this.load();
	}

	protected void doStop() throws Throwable
	{
		this.unLoad();
	}

	private void load() throws Throwable
	{
		String queryPath = Application.getSystemPath() + QUERY_PATH;
		File queryDir = new File(queryPath);
		File[] queryFiles = FileUtils.filterFiles(queryDir, FILE_PATTERN, true);

		for (int i = 0; i < queryFiles.length; i++)
		{
			String fileName = queryFiles[i].getName().toLowerCase();
			String defaultQueryName = fileName.substring(0, fileName.indexOf(".xml"));
			Document doc = XmlUtils.createDocument(queryFiles[i], false);
			QueryTemplate qt = new QueryTemplateImpl(doc.getRootElement(), defaultQueryName);
			cache.put(new Element(qt.getName(), qt));
			logger.info("load query define [{}]", qt.getClazz());
		}
	}

	private void unLoad()
	{
		cache.removeAll();
	}

	public QueryTemplate getQueryTemplate(String name)
	{
		return (QueryTemplate) cache.get(name).getValue();
	}

	public QueryTemplate getQueryTemplate(Class<?> clazz)
	{
		return getQueryTemplate(clazz.getName());
	}
}
