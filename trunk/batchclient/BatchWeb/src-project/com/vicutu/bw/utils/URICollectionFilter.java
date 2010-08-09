package com.vicutu.bw.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.NOPTransformer;

public class URICollectionFilter {

	private Collection<String> innerCollection;

	public static URICollectionFilter valueOf(Collection<String> inputCollection) {
		return new URICollectionFilter(inputCollection);
	}

	private URICollectionFilter() {
		innerCollection = null;
	}

	@SuppressWarnings("unchecked")
	private URICollectionFilter(Collection<String> input) {
		super();
		innerCollection = CollectionUtils.collect(input, NOPTransformer.INSTANCE);
	}

	private URICollectionFilter clearAndSwap(Collection<String> c) {
		innerCollection.clear();
		innerCollection = c;
		return this;
	}

	public Collection<String> collection() {
		return innerCollection;
	}

	public List<String> list() {
		if (innerCollection instanceof List<?>) {
			return (List<String>) innerCollection;
		} else {
			return new ArrayList<String>(innerCollection);
		}
	}

	public Set<String> set() {
		if (innerCollection instanceof Set<?>) {
			return (Set<String>) innerCollection;
		} else {
			return new HashSet<String>(innerCollection);
		}
	}

	public URICollectionFilter removeDuplicate() {
		return clearAndSwap(URIUtils.removeDuplicate(innerCollection));
	}

	public URICollectionFilter removeStartsWith(boolean caseSensitive, String... prefixes) {
		return clearAndSwap(URIUtils.removeStartsWith(innerCollection, caseSensitive, prefixes));
	}

	public URICollectionFilter removeStartsWith(String... prefixes) {
		return removeStartsWith(true, prefixes);
	}

	public URICollectionFilter selectStartsWith(boolean caseSensitive, String... prefixes) {
		return clearAndSwap(URIUtils.selectStartsWith(innerCollection, caseSensitive, prefixes));
	}

	public URICollectionFilter selectStartsWith(String... prefixes) {
		return selectStartsWith(true, prefixes);
	}

	public URICollectionFilter removeEndsWith(boolean caseSensitive, String... suffixes) {
		return clearAndSwap(URIUtils.removeEndsWith(innerCollection, caseSensitive, suffixes));
	}

	public URICollectionFilter removeEndsWith(String... suffixes) {
		return removeEndsWith(true, suffixes);
	}

	public URICollectionFilter selectEndsWith(boolean caseSensitive, String... suffixes) {
		return clearAndSwap(URIUtils.selectEndsWith(innerCollection, caseSensitive, suffixes));
	}

	public URICollectionFilter selectEndsWith(String... suffixes) {
		return selectEndsWith(true, suffixes);
	}

	public URICollectionFilter removeContains(boolean caseSensitive, String... contents) {
		return clearAndSwap(URIUtils.removeContains(innerCollection, caseSensitive, contents));
	}

	public URICollectionFilter removeContains(String... contents) {
		return removeContains(true, contents);
	}

	public URICollectionFilter selectContains(boolean caseSensitive, String... contents) {
		return clearAndSwap(URIUtils.selectContains(innerCollection, caseSensitive, contents));
	}

	public URICollectionFilter selectContains(String... contents) {
		return selectContains(true, contents);
	}

	public URICollectionFilter removeContainsPattern(String... regexes) {
		return clearAndSwap(URIUtils.removeContainsPattern(innerCollection, regexes));
	}

	public URICollectionFilter selectContainsPattern(String... regexes) {
		return clearAndSwap(URIUtils.selectContainsPattern(innerCollection, regexes));
	}

	public URICollectionFilter remove(Predicate... predicates) {
		return clearAndSwap(URIUtils.remove(innerCollection, predicates));
	}

	public URICollectionFilter select(Predicate... predicates) {
		return clearAndSwap(URIUtils.select(innerCollection, predicates));
	}
}
