package com.vicutu.persistence.query;

import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class CriterionSource {

	protected CriterionRestriction restriction;

	protected Object value;

	protected String property;

	protected CriterionLikeMatchMode matchMode;

	public CriterionSource() {
	}

	public CriterionSource(String property, Object value, CriterionRestriction restriction,
			CriterionLikeMatchMode matchMode) {
		this.restriction = restriction;
		this.property = property;
		this.value = value;
		this.matchMode = matchMode;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String attribute) {
		this.property = attribute;
	}

	public Criterion buildCriterion() {
		if (property == null || (value instanceof String && "".equals(value))) {
			return null;
		}

		switch (restriction) {
		case EQUAL:
			return Restrictions.eq(property, value);
		case NOT_EQUAL:
			return Restrictions.ne(property, value);
		case GREATER_EQUAL:
			return Restrictions.ge(property, value);
		case GREATER:
			return Restrictions.gt(property, value);
		case LESS_EQUAL:
			return Restrictions.le(property, value);
		case LESS:
			return Restrictions.lt(property, value);
		case IS_EMPTY:
			return Restrictions.isEmpty(property);
		case IS_NOT_EMPTY:
			return Restrictions.isNotEmpty(property);
		case IS_NULL:
			return Restrictions.isNull(property);
		case IS_NOT_NULL:
			return Restrictions.isNotNull(property);
		case LIKE:
			return dealWithLike();
		case IN:
			return Restrictions.in(property, (Collection<?>) value);
		}
		return null;
	}

	private Criterion dealWithLike() {

		switch (matchMode) {
		case ANYWHERE:
			return Restrictions.like(property, value.toString(), MatchMode.ANYWHERE);
		case START:
			return Restrictions.like(property, value.toString(), MatchMode.START);
		case END:
			return Restrictions.like(property, value.toString(), MatchMode.END);
		case EXACT:
			return Restrictions.like(property, value.toString(), MatchMode.EXACT);
		}
		return null;
	}
}
