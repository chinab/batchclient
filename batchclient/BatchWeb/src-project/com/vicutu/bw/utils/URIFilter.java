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
		return new URIFilter(URIUtils.removeDuplicate(uris));
	}

	public URIFilter removeStartsWith(boolean caseSensitive, String... prefixes) {
		return new URIFilter(URIUtils.removeStartsWith(uris, caseSensitive, prefixes));
	}

	public URIFilter removeStartsWith(String... prefixes) {
		return removeStartsWith(true, prefixes);
	}

	public URIFilter selectStartsWith(boolean caseSensitive, String... prefixes) {
		return new URIFilter(URIUtils.selectStartsWith(uris, caseSensitive, prefixes));
	}

	public URIFilter selectStartsWith(String... prefixes) {
		return selectStartsWith(true, prefixes);
	}

	public URIFilter removeEndsWith(boolean caseSensitive, String... suffixes) {
		return new URIFilter(URIUtils.removeEndsWith(uris, caseSensitive, suffixes));
	}

	public URIFilter removeEndsWith(String... suffixes) {
		return removeEndsWith(true, suffixes);
	}

	public URIFilter selectEndsWith(boolean caseSensitive, String... suffixes) {
		return new URIFilter(URIUtils.selectEndsWith(uris, caseSensitive, suffixes));
	}

	public URIFilter selectEndsWith(String... suffixes) {
		return selectEndsWith(true, suffixes);
	}

	public URIFilter removeContains(boolean caseSensitive, String... contents) {
		return new URIFilter(URIUtils.removeContains(uris, caseSensitive, contents));
	}

	public URIFilter removeContains(String... contents) {
		return removeContains(true, contents);
	}

	public URIFilter selectContains(boolean caseSensitive, String... contents) {
		return new URIFilter(URIUtils.selectContains(uris, caseSensitive, contents));
	}

	public URIFilter selectContains(String... contents) {
		return selectContains(true, contents);
	}

	public URIFilter removeContainsPattern(String... regexes) {
		return new URIFilter(URIUtils.removeContainsPattern(uris, regexes));
	}

	public URIFilter selectContainsPattern(String... regexes) {
		return new URIFilter(URIUtils.selectContainsPattern(uris, regexes));
	}

	public URIFilter remove(Predicate... predicates) {
		return new URIFilter(URIUtils.remove(uris, predicates));
	}

	public URIFilter select(Predicate... predicates) {
		return new URIFilter(URIUtils.select(uris, predicates));
	}
}
