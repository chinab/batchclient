package com.vicutu.batchdownload.utils.functors;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

public class ContentPredicate implements Predicate {

	private final boolean caseSensitive;

	private final String content;

	public ContentPredicate(String content, boolean caseSensitive) {
		super();
		this.caseSensitive = caseSensitive;
		this.content = content;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public String getContent() {
		return content;
	}

	@Override
	public boolean evaluate(Object obj) {
		if (obj == null || content == null) {
			return false;
		}
		String input = obj.toString();
		if (caseSensitive) {
			return StringUtils.contains(input, content);
		} else {
			return StringUtils.containsIgnoreCase(input, content);
		}
	}

}
