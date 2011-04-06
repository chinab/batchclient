package com.vicutu.persistence.query.impl;

import java.util.Iterator;

import org.dom4j.Element;

import com.vicutu.commons.xml.XmlUtils;
import com.vicutu.persistence.query.AbstractQueryTemplate;
import com.vicutu.persistence.query.CriterionLikeMatchMode;
import com.vicutu.persistence.query.CriterionRestriction;
import com.vicutu.persistence.query.CriterionSource;

public class XmlQueryTemplateImpl extends AbstractQueryTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8989408423361693879L;

	public XmlQueryTemplateImpl(Element e, String defaultQueryName) {
		if (e == null) {
			return;
		}
		this.name = XmlUtils.getAttribute(e, "name", defaultQueryName);
		this.clazz = XmlUtils.getAttribute(e, "class", null);
		Iterator<?> it = e.elementIterator();
		while (it.hasNext()) {
			Element propertyDef = (Element) it.next();
			String name = XmlUtils.getAttribute(propertyDef, "name", null);
			String target = XmlUtils.getAttribute(propertyDef, "target", name);
			CriterionRestriction restriction = CriterionRestriction.valueOf(XmlUtils.getAttribute(propertyDef,
					"restriction", "EQUAL"));
			CriterionLikeMatchMode matchMode = CriterionLikeMatchMode.valueOf(XmlUtils.getAttribute(propertyDef,
					"matchMode", "ANYWHERE"));
			CriterionSource criterionSource = new CriterionSource(target, null, restriction, matchMode);
			criterions.put(name, criterionSource);
		}
	}
}
