package com.vicutu.persistence.query.impl;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.hibernate.criterion.DetachedCriteria;

import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.persistence.query.DetachedCriteriaBuilder;
import com.vicutu.persistence.query.QueryTemplate;

public class AnnotationDetachedCriteriaBuilderBean implements DetachedCriteriaBuilder {

	private static Logger logger = LoggerFactory.getLogger(AnnotationDetachedCriteriaBuilderBean.class);

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	@Override
	public DetachedCriteria build(Object source) {
		String templateName = source.getClass().getName();
		QueryTemplate queryTemplate = (QueryTemplate) cache.get(templateName).getValue();

		if (queryTemplate != null) {
			logger.debug("not found QueryTemplate : {}, so created it", templateName);
			queryTemplate = new AnnotationQueryTemplateImpl(source);
			cache.put(new Element(queryTemplate.getName(), queryTemplate));
		} else {
			logger.debug("found QueryTemplate : {}", templateName);
		}
		queryTemplate.setValues(source);
		return queryTemplate.buildDetachedCriteria();
	}
}
