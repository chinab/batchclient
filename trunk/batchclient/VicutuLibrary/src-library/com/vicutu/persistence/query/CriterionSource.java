package com.vicutu.persistence.query;

import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class CriterionSource
{
	public static final String OPERATOR_EQUAL = "eq";

	public static final String OPERATOR_NOT_EQUAL = "ne";

	public static final String OPERATOR_GREATER_EQUAL = "ge";

	public static final String OPERATOR_GREATERL = "gt";

	public static final String OPERATOR_LESS_EQUAL = "le";

	public static final String OPERATOR_LESS = "lt";

	public static final String OPERATOR_IS_EMPTY = "isEmpty";

	public static final String OPERATOR_IS_NOT_EMPTY = "isNotEmpty";

	public static final String OPERATOR_IS_NULL = "isNull";

	public static final String OPERATOR_IS_NOT_NULL = "isNotNull";

	public static final String OPERATOR_LIKE = "like";

	public static final String OPERATOR_IN = "in";

	public static final String MATCHMODE_ANYWHERE = "anywhere";

	public static final String MATCHMODE_START = "start";

	public static final String MATCHMODE_END = "end";

	public static final String MATCHMODE_EXACT = "exact";

	protected String operator;

	protected Object value;

	protected String property;

	protected String matchMode;

	public CriterionSource()
	{
	}

	public CriterionSource(String property, Object value, String operator, String matchMode)
	{
		this.operator = operator;
		this.property = property;
		this.value = value;
		this.matchMode = matchMode;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public String getProperty()
	{
		return property;
	}

	public void setProperty(String attribute)
	{
		this.property = attribute;
	}

	public Criterion buildCriterion()
	{
		if (property == null)
		{
			return null;
		}
		if (operator == null)
		{
			operator = OPERATOR_EQUAL;
		}

		if (value instanceof String && "".equals(value))
		{
			return null;
		}

		//Criterion criterion = null;
		// eq
		if (OPERATOR_EQUAL.equalsIgnoreCase(operator) && value != null)
		{
			return Restrictions.eq(property, value);
		}
		// ne
		else if (OPERATOR_NOT_EQUAL.equalsIgnoreCase(operator) && value != null)
		{
			return Restrictions.ne(property, value);
		}
		// ge
		else if (OPERATOR_GREATER_EQUAL.equalsIgnoreCase(operator) && value != null)
		{
			return Restrictions.ge(property, value);
		}
		// gt
		else if (OPERATOR_GREATERL.equalsIgnoreCase(operator) && value != null)
		{
			return Restrictions.gt(property, value);
		}
		// le
		else if (OPERATOR_LESS_EQUAL.equalsIgnoreCase(operator) && value != null)
		{
			return Restrictions.le(property, value);
		}
		// lt
		else if (OPERATOR_LESS.equalsIgnoreCase(operator) && value != null)
		{
			return Restrictions.lt(property, value);
		}
		// is empty
		else if (OPERATOR_IS_EMPTY.equalsIgnoreCase(operator))
		{
			return Restrictions.isEmpty(property);
		}
		// is not empty
		else if (OPERATOR_IS_NOT_EMPTY.equalsIgnoreCase(operator))
		{
			return Restrictions.isNotEmpty(property);
		}
		// is null
		else if (OPERATOR_IS_NULL.equalsIgnoreCase(operator))
		{
			return Restrictions.isNull(property);
		}
		// is not null
		else if (OPERATOR_IS_NOT_NULL.equalsIgnoreCase(operator))
		{
			return Restrictions.isNotNull(property);
		}
		// in
		else if (OPERATOR_IN.equalsIgnoreCase(operator) && value != null)
		{
			return Restrictions.in(property, (Collection<?>) value);
		}
		// like
		else if (OPERATOR_LIKE.equalsIgnoreCase(operator) && value != null)
		{
			if (matchMode == null)
			{
				matchMode = MATCHMODE_ANYWHERE;
			}
			else if (MATCHMODE_ANYWHERE.equalsIgnoreCase(matchMode))
			{
				return Restrictions.like(property, value.toString(), MatchMode.ANYWHERE);
			}
			else if (MATCHMODE_START.equalsIgnoreCase(matchMode))
			{
				return Restrictions.like(property, value.toString(), MatchMode.START);
			}
			else if (MATCHMODE_END.equalsIgnoreCase(matchMode))
			{
				return Restrictions.like(property, value.toString(), MatchMode.END);
			}
			else if (MATCHMODE_EXACT.equalsIgnoreCase(matchMode))
			{
				return Restrictions.like(property, value.toString(), MatchMode.EXACT);
			}
		}
		return null;
	}

	public String getMatchMode()
	{
		return matchMode;
	}

	public void setMatchMode(String matchMode)
	{
		this.matchMode = matchMode;
	}
}
