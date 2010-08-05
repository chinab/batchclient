package com.vicutu.bw.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.NOPTransformer;

public class URIFilter {

	private Collection<String> innerCollection;

	public static URIFilter valueOf(Collection<String> inputCollection) {
		return new URIFilter(inputCollection);
	}

	private URIFilter() {
		innerCollection = null;
	}

	@SuppressWarnings("unchecked")
	private URIFilter(Collection<String> input) {
		super();
		innerCollection = CollectionUtils.collect(input, NOPTransformer.INSTANCE);
	}

	private URIFilter clearAndSwap(Collection<String> c) {
		innerCollection.clear();
		innerCollection = c;
		return this;
	}

	public Collection<String> collection() {
		return innerCollection;
	}
	
	public List<String> list(){
		if(innerCollection instanceof List<?>){
			return (List<String>)innerCollection;
		}else{
			return new ArrayList<String>(innerCollection);
		}
	}
	
	public Set<String> set(){
		if(innerCollection instanceof Set<?>){
			return (Set<String>)innerCollection;
		}else{
			return new HashSet<String>(innerCollection);
		}
	}

	public URIFilter removeDuplicate() {
		return clearAndSwap(URIUtils.removeDuplicate(innerCollection));
	}

	public URIFilter removeStartsWith(boolean caseSensitive, String... prefixes) {
		return clearAndSwap(URIUtils.removeStartsWith(innerCollection, caseSensitive, prefixes));
	}

	public URIFilter removeStartsWith(String... prefixes) {
		return removeStartsWith(true, prefixes);
	}

	public URIFilter selectStartsWith(boolean caseSensitive, String... prefixes) {
		return clearAndSwap(URIUtils.selectStartsWith(innerCollection, caseSensitive, prefixes));
	}

	public URIFilter selectStartsWith(String... prefixes) {
		return selectStartsWith(true, prefixes);
	}

	public URIFilter removeEndsWith(boolean caseSensitive, String... suffixes) {
		return clearAndSwap(URIUtils.removeEndsWith(innerCollection, caseSensitive, suffixes));
	}

	public URIFilter removeEndsWith(String... suffixes) {
		return removeEndsWith(true, suffixes);
	}

	public URIFilter selectEndsWith(boolean caseSensitive, String... suffixes) {
		return clearAndSwap(URIUtils.selectEndsWith(innerCollection, caseSensitive, suffixes));
	}

	public URIFilter selectEndsWith(String... suffixes) {
		return selectEndsWith(true, suffixes);
	}

	public URIFilter removeContains(boolean caseSensitive, String... contents) {
		return clearAndSwap(URIUtils.removeContains(innerCollection, caseSensitive, contents));
	}

	public URIFilter removeContains(String... contents) {
		return removeContains(true, contents);
	}

	public URIFilter selectContains(boolean caseSensitive, String... contents) {
		return clearAndSwap(URIUtils.selectContains(innerCollection, caseSensitive, contents));
	}

	public URIFilter selectContains(String... contents) {
		return selectContains(true, contents);
	}

	public URIFilter removeContainsPattern(String... regexes) {
		return clearAndSwap(URIUtils.removeContainsPattern(innerCollection, regexes));
	}

	public URIFilter selectContainsPattern(String... regexes) {
		return clearAndSwap(URIUtils.selectContainsPattern(innerCollection, regexes));
	}

	public URIFilter remove(Predicate... predicates) {
		return clearAndSwap(URIUtils.remove(innerCollection, predicates));
	}

	public URIFilter select(Predicate... predicates) {
		return clearAndSwap(URIUtils.select(innerCollection, predicates));
	}
}
