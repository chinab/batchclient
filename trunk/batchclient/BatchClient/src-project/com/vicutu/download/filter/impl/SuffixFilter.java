package com.vicutu.download.filter.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.vicutu.download.filter.URLFilter;

public class SuffixFilter implements URLFilter {
	private String restricts;

	private String excludes;

	private Set<String> restrictSuffixs;

	private Set<String> excludeSuffixs;

	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}

	public void setExcludeSuffixs(Set<String> excludeSuffixs) {
		this.excludeSuffixs = excludeSuffixs;
	}

	public void initialize() {
		if (restricts != null) {
			restrictSuffixs = new HashSet<String>();
			String[] restrictArray = restricts.split(",");
			for (int i = 0; i < restrictArray.length; i++) {
				restrictSuffixs.add(restrictArray[i].toLowerCase());
			}
		}
		if (excludes != null) {
			excludeSuffixs = new HashSet<String>();
			String[] excludeArray = excludes.split(",");
			for (int i = 0; i < excludeArray.length; i++) {
				excludeSuffixs.add(excludeArray[i].toLowerCase());
			}
		}
	}

	public boolean filt(String url) {

		String suffix = StringUtils.substringAfterLast(url, ".");

		if (restrictSuffixs != null) {
			if (restrictSuffixs.contains(suffix.toLowerCase())) {
				return true;
			} else {
				return false;
			}
		} else {
			if (excludeSuffixs != null && excludeSuffixs.contains(suffix.toLowerCase())) {
				return false;
			} else {
				return true;
			}
		}
	}
}
