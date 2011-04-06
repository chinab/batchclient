package com.vicutu.batchdownload.utils.functors;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

public class SuffixPredicate implements Predicate {

	private final boolean caseSensitive;

	private final String suffix;

	public SuffixPredicate(String suffix, boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
		this.suffix = suffix;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public String getSuffix() {
		return suffix;
	}

	@Override
	public boolean evaluate(Object obj) {
		if (obj == null || suffix == null) {
			return false;
		}
		String input = obj.toString();
		if (caseSensitive) {
			return StringUtils.endsWith(input, suffix);
		} else {
			return StringUtils.endsWithIgnoreCase(input, suffix);
		}
	}
}
