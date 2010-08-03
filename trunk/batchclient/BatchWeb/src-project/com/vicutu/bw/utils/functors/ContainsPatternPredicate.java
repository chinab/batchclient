package com.vicutu.bw.utils.functors;

import java.util.regex.Pattern;

import org.apache.commons.collections.Predicate;

public class ContainsPatternPredicate implements Predicate {

	private final Pattern pattern;

	public ContainsPatternPredicate(Pattern pattern) {
		this.pattern = pattern;
	}
	
	public ContainsPatternPredicate(String regex) {
		this(Pattern.compile(regex));
	}

	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public boolean evaluate(Object obj) {
		if (obj == null) {
			return false;
		}
		return pattern.matcher(obj.toString()).find();
	}
}
