package com.vicutu.bw.utils;

import java.util.Collection;

import org.apache.commons.collections.Predicate;

public class URIFilter {

	private final Collection<String> uris;

	public URIFilter(Collection<String> uris) {
		super();
		this.uris = uris;
	}

	public Collection<String> result() {
		return uris;
	}

	public URIFilter removeDuplicate() {
		URIFilter filter = new URIFilter(URIUtils.removeDuplicate(uris));
		uris.clear();
		return filter;
	}

	public URIFilter removeStartsWith(boolean caseSensitive, String... prefixes) {
		URIFilter filter = new URIFilter(URIUtils.removeStartsWith(uris, caseSensitive, prefixes));
		uris.clear();
		return filter;
	}

	public URIFilter removeStartsWith(String... prefixes) {
		return removeStartsWith(true, prefixes);
	}

	public URIFilter selectStartsWith(boolean caseSensitive, String... prefixes) {
		URIFilter filter = new URIFilter(URIUtils.selectStartsWith(uris, caseSensitive, prefixes));
		uris.clear();
		return filter;
	}

	public URIFilter selectStartsWith(String... prefixes) {
		return selectStartsWith(true, prefixes);
	}

	public URIFilter removeEndsWith(boolean caseSensitive, String... suffixes) {
		URIFilter filter = new URIFilter(URIUtils.removeEndsWith(uris, caseSensitive, suffixes));
		uris.clear();
		return filter;
	}

	public URIFilter removeEndsWith(String... suffixes) {
		return removeEndsWith(true, suffixes);
	}

	public URIFilter selectEndsWith(boolean caseSensitive, String... suffixes) {
		URIFilter filter = new URIFilter(URIUtils.selectEndsWith(uris, caseSensitive, suffixes));
		uris.clear();
		return filter;
	}

	public URIFilter selectEndsWith(String... suffixes) {
		return selectEndsWith(true, suffixes);
	}

	public URIFilter removeContains(boolean caseSensitive, String... contents) {
		URIFilter filter = new URIFilter(URIUtils.removeContains(uris, caseSensitive, contents));
		uris.clear();
		return filter;
	}

	public URIFilter removeContains(String... contents) {
		return removeContains(true, contents);
	}

	public URIFilter removeContainsPattern(String... regexes) {
		URIFilter filter = new URIFilter(URIUtils.removeContainsPattern(uris, regexes));
		uris.clear();
		return filter;
	}

	public URIFilter selectContainsPattern(String... regexes) {
		URIFilter filter = new URIFilter(URIUtils.selectContainsPattern(uris, regexes));
		uris.clear();
		return filter;
	}

	public URIFilter remove(Predicate... predicates) {
		URIFilter filter = new URIFilter(URIUtils.remove(uris, predicates));
		uris.clear();
		return filter;
	}

	public URIFilter select(Predicate... predicates) {
		URIFilter filter = new URIFilter(URIUtils.select(uris, predicates));
		uris.clear();
		return filter;
	}
}
