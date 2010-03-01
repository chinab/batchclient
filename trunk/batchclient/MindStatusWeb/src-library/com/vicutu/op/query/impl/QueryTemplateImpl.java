package com.vicutu.op.query.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.dom4j.Element;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import com.vicutu.commons.xml.XmlUtils;
import com.vicutu.op.query.CriterionSource;
import com.vicutu.op.query.QueryTemplate;

public class QueryTemplateImpl implements QueryTemplate {

	protected ListOrderedMap criterions = new ListOrderedMap();

	protected String name;

	protected String clazz;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QueryTemplateImpl() {

	}

	public QueryTemplateImpl(Element e, String defaultQueryName) {
		if (e == null) {
			return;
		}
		this.name = XmlUtils.getAttribute(e, "name", defaultQueryName);
		this.clazz = XmlUtils.getAttribute(e, "class", null);
		Iterator<?> it = e.elementIterator();
		int i = 0;
		while (it.hasNext()) {
			Element propertyDef = (Element) it.next();
			int index = XmlUtils.getAttribute(propertyDef, "index", i);
			String name = XmlUtils.getAttribute(propertyDef, "name", null);
			String obj = XmlUtils.getAttribute(propertyDef, "obj", name);
			String operator = XmlUtils.getAttribute(propertyDef, "operator",
					CriterionSource.OPERATOR_EQUAL);
			String match = XmlUtils.getAttribute(propertyDef, "match", null);
			CriterionSource criterionSource = new CriterionSource(obj, null,
					operator, match);
			criterions.put(index, name, criterionSource);
			i++;
		}
	}

	public Object getValue(int index) {
		CriterionSource criterionSource = (CriterionSource) criterions
				.getValue(index);
		return criterionSource.getValue();
	}

	public Object getValue(String name) {
		CriterionSource criterionSource = (CriterionSource) criterions
				.get(name);
		return criterionSource.getValue();
	}

	public void setValue(int index, Object value) {
		CriterionSource criterionSource = (CriterionSource) criterions
				.getValue(index);
		criterionSource.setValue(value);
	}

	public void setValue(String name, Object value) {
		CriterionSource criterionSource = (CriterionSource) criterions
				.get(name);
		criterionSource.setValue(value);
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	
	public void setValues(Object bean) {
		try {
			Map<?, ?> m = PropertyUtils.describe(bean);
			this.setValues(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void setValues(Map<?, ?> m) {
		Set<?> keySet = criterions.keySet();
		Iterator<?> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			Object value = m.get(key);
			this.setValue(key, value);
		}
	}

	
	public DetachedCriteria buildDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forEntityName(this.clazz);
		Iterator<?> it = criterions.values().iterator();
		while (it.hasNext()) {
			CriterionSource cs = (CriterionSource) it.next();
			Criterion criterion = cs.buildCriterion();
			if (criterion != null) {
				detachedCriteria = detachedCriteria.add(criterion);
			}
		}
		return detachedCriteria;
	}
}
