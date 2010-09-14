package com.vicutu.bw.utils.functors;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

public class PrefixPredicate implements Predicate {

	private final boolean caseSensitive;

	private final String prefix;

	public PrefixPredicate(String prefix, boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
		this.prefix = prefix;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public String getPrefix() {
		return prefix;
	}

	@Override
	public boolean evaluate(Object obj) {
		if (obj == null || prefix == null) {
			return false;
		}
		String input = obj.toString();
		String tempStr = input;
		if (StringUtils.startsWithIgnoreCase(input, "http://")) {
			tempStr = StringUtils.removeStartIgnoreCase(input, "http://");
		} else if (StringUtils.startsWithIgnoreCase(input, "https://")) {
			tempStr = StringUtils.removeStartIgnoreCase(input, "https://");
		}
		if (caseSensitive) {
			return StringUtils.startsWith(tempStr, prefix);
		} else {
			return StringUtils.startsWithIgnoreCase(tempStr, prefix);
		}
	}
}
