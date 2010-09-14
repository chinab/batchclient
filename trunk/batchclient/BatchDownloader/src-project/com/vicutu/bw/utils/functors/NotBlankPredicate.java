package com.vicutu.bw.utils.functors;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

public class NotBlankPredicate implements Predicate {

	public static final NotBlankPredicate INSTANCE = new NotBlankPredicate();

	@Override
	public boolean evaluate(Object obj) {
		return !StringUtils.isBlank((String) obj);
	}
}
