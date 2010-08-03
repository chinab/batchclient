package com.vicutu.bw.utils.functors;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

public class BlankPredicate implements Predicate {

	public static final BlankPredicate INSTANCE = new BlankPredicate();

	@Override
	public boolean evaluate(Object obj) {
		return StringUtils.isBlank((String) obj);
	}
}
