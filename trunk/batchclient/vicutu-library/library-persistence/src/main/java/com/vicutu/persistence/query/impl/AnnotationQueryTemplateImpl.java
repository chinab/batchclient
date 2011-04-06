package com.vicutu.persistence.query.impl;

import java.lang.reflect.Field;

import com.vicutu.persistence.query.AbstractQueryTemplate;
import com.vicutu.persistence.query.CriterionLikeMatchMode;
import com.vicutu.persistence.query.CriterionRestriction;
import com.vicutu.persistence.query.CriterionSource;
import com.vicutu.persistence.query.annotation.QueryProperty;
import com.vicutu.persistence.query.annotation.QueryTemplate;

public class AnnotationQueryTemplateImpl extends AbstractQueryTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2911652246930138886L;

	public AnnotationQueryTemplateImpl(Object queryBean) {
		Class<?> queryBeanClass = queryBean.getClass();

		QueryTemplate queryTemplateAnnotation = queryBeanClass.getAnnotation(QueryTemplate.class);
		if (queryTemplateAnnotation == null) {
			// TODO deal with null logic
		}

		String queryTemplateName = queryTemplateAnnotation.name();
		this.name = "".equals(queryTemplateName) ? queryBeanClass.getName() : queryTemplateName;
		Class<?> queryTemplateClass = queryTemplateAnnotation.clazz();
		this.clazz = queryTemplateClass.getName();

		Field[] fields = queryBeanClass.getDeclaredFields();
		for (Field field : fields) {
			QueryProperty queryProperty = field.getAnnotation(QueryProperty.class);
			if (queryProperty != null) {
				String fieldName = field.getName();
				String target = "".equals(queryProperty.target()) ? fieldName : queryProperty.target();
				CriterionRestriction restriction = queryProperty.restriction();
				CriterionLikeMatchMode matchMode = queryProperty.matchMode();
				CriterionSource criterionSource = new CriterionSource(target, null, restriction, matchMode);
				criterions.put(name, criterionSource);
			}
		}
	}
}
