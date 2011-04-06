package com.vicutu.persistence.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

public class AbstractQueryTemplate implements QueryTemplate, Serializable {

	private static final long serialVersionUID = 5903152124873226652L;

	protected Map<String, CriterionSource> criterions = new HashMap<String, CriterionSource>();

	protected String name;

	protected String clazz;

	@Override
	public DetachedCriteria buildDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forEntityName(this.clazz);
		for (CriterionSource cs : criterions.values()) {
			Criterion criterion = cs.buildCriterion();
			if (criterion != null) {
				detachedCriteria = detachedCriteria.add(criterion);
			}
		}
		return detachedCriteria;
	}

	@Override
	public String getClazz() {
		return clazz;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getValue(String name) {
		CriterionSource criterionSource = criterions.get(name);
		return criterionSource != null ? criterionSource.getValue() : null;
	}

	@Override
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setValue(String name, Object value) {
		CriterionSource criterionSource = (CriterionSource) criterions.get(name);
		if (criterionSource != null) {
			criterionSource.setValue(value);
		}
	}

	@Override
	public void setValues(Object pojo) {
		try {
			Map<?, ?> m = PropertyUtils.describe(pojo);
			this.setValues(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setValues(Map<?, ?> m) {
		Set<String> keySet = criterions.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Object value = m.get(key);
			this.setValue(key, value);
		}
	}
}
