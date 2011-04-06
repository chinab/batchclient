package com.vicutu.persistence.query.impl;

import java.io.File;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.hibernate.criterion.DetachedCriteria;

import com.vicutu.commons.config.Application;
import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.commons.xml.XmlUtils;
import com.vicutu.persistence.query.DetachedCriteriaBuilder;
import com.vicutu.persistence.query.QueryTemplate;

public class XmlDetachedCriteriaBuilderBean implements DetachedCriteriaBuilder {

	private static Logger logger = LoggerFactory.getLogger(XmlDetachedCriteriaBuilderBean.class);

	private static final String FILE_PATTERN = "[\\S]*.xml";

	private static final String QUERY_PATH = "resource/query";

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public void init() {
		logger.info("loading query templates...");
		this.load();
	}

	public void destroy() {
		logger.info("unloading query templates...");
		this.unLoad();
	}

	private void load() {
		String queryPath = Application.getSystemPath() + QUERY_PATH;
		File queryDir = new File(queryPath);
		File[] queryFiles = FileUtils.filterFiles(queryDir, FILE_PATTERN, true);

		for (int i = 0; i < queryFiles.length; i++) {
			String fileName = queryFiles[i].getName().toLowerCase();
			String defaultQueryName = fileName.substring(0, fileName.indexOf(".xml"));
			Document doc;
			try {
				doc = XmlUtils.createDocument(queryFiles[i], false);
				QueryTemplate qt = new XmlQueryTemplateImpl(doc.getRootElement(), defaultQueryName);
				cache.put(new Element(qt.getName(), qt));
				logger.info("load query define [{}]", qt.getClazz());
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void unLoad() {
		cache.removeAll();
	}

	@Override
	public DetachedCriteria build(Object source) {
		String templateName = source.getClass().getName();
		QueryTemplate queryTemplate = (QueryTemplate) cache.get(templateName).getValue();
		return queryTemplate.buildDetachedCriteria();
	}

}
