package com.vicutu.batchdownload.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.AndPredicate;
import org.apache.commons.collections.functors.AnyPredicate;
import org.apache.commons.collections.functors.OrPredicate;
import org.apache.commons.collections.set.ListOrderedSet;

import com.vicutu.batchdownload.utils.functors.BlankPredicate;
import com.vicutu.batchdownload.utils.functors.ContentPredicate;
import com.vicutu.batchdownload.utils.functors.NotBlankPredicate;
import com.vicutu.batchdownload.utils.functors.PrefixPredicate;
import com.vicutu.batchdownload.utils.functors.ContainsPatternPredicate;
import com.vicutu.batchdownload.utils.functors.SuffixPredicate;

public class URIUtils {

	private URIUtils() {
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> removeDuplicate(Collection<String> input) {
		Collection<String> c = null;
		if (input instanceof Set) {
			c = new ArrayList<String>(input);
		} else {
			c = new ListOrderedSet();
			c.addAll(input);
		}
		return CollectionUtils.selectRejected(c, BlankPredicate.INSTANCE);
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> removeStartsWith(Collection<String> input, boolean caseSensitive,
			String... prefixes) {
		List<Predicate> predicates = new ArrayList<Predicate>(prefixes.length + 1);
		predicates.add(BlankPredicate.INSTANCE);
		for (String prefix : prefixes) {
			predicates.add(new PrefixPredicate(prefix, caseSensitive));
		}
		return CollectionUtils.selectRejected(input,
				new AnyPredicate(predicates.toArray(new Predicate[predicates.size()])));
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> selectStartsWith(Collection<String> input, boolean caseSensitive,
			String... prefixes) {
		List<Predicate> predicates = new ArrayList<Predicate>(prefixes.length);
		for (String prefix : prefixes) {
			predicates.add(new PrefixPredicate(prefix, caseSensitive));
		}
		return CollectionUtils.select(
				input,
				new AndPredicate(NotBlankPredicate.INSTANCE, new AnyPredicate(predicates
						.toArray(new Predicate[predicates.size()]))));
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> removeEndsWith(Collection<String> input, boolean caseSensitive, String... suffixes) {
		List<Predicate> predicates = new ArrayList<Predicate>(suffixes.length + 1);
		predicates.add(BlankPredicate.INSTANCE);
		for (String suffix : suffixes) {
			predicates.add(new SuffixPredicate(suffix, caseSensitive));
		}
		return CollectionUtils.selectRejected(input,
				new AnyPredicate(predicates.toArray(new Predicate[predicates.size()])));
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> selectEndsWith(Collection<String> input, boolean caseSensitive, String... suffixes) {
		List<Predicate> predicates = new ArrayList<Predicate>(suffixes.length);
		for (String suffix : suffixes) {
			predicates.add(new SuffixPredicate(suffix, caseSensitive));
		}
		return CollectionUtils.select(
				input,
				new AndPredicate(NotBlankPredicate.INSTANCE, new AnyPredicate(predicates
						.toArray(new Predicate[predicates.size()]))));
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> removeContains(Collection<String> input, boolean caseSensitive, String... contents) {
		List<Predicate> predicates = new ArrayList<Predicate>(contents.length + 1);
		predicates.add(BlankPredicate.INSTANCE);
		for (String content : contents) {
			predicates.add(new ContentPredicate(content, caseSensitive));
		}
		return CollectionUtils.selectRejected(input,
				new AnyPredicate(predicates.toArray(new Predicate[predicates.size()])));
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> selectContains(Collection<String> input, boolean caseSensitive, String... contents) {
		List<Predicate> predicates = new ArrayList<Predicate>(contents.length);
		for (String content : contents) {
			predicates.add(new ContentPredicate(content, caseSensitive));
		}
		return CollectionUtils.select(
				input,
				new AndPredicate(NotBlankPredicate.INSTANCE, new AnyPredicate(predicates
						.toArray(new Predicate[predicates.size()]))));
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> removeContainsPattern(Collection<String> input, String... regexes) {
		List<Predicate> predicates = new ArrayList<Predicate>(regexes.length + 1);
		predicates.add(BlankPredicate.INSTANCE);
		for (String regex : regexes) {
			predicates.add(new ContainsPatternPredicate(regex));
		}
		return CollectionUtils.selectRejected(input,
				new AnyPredicate(predicates.toArray(new Predicate[predicates.size()])));
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> selectContainsPattern(Collection<String> input, String... regexes) {
		List<Predicate> predicates = new ArrayList<Predicate>(regexes.length);
		for (String regex : regexes) {
			predicates.add(new ContainsPatternPredicate(regex));
		}
		return CollectionUtils.select(
				input,
				new AndPredicate(NotBlankPredicate.INSTANCE, new AnyPredicate(predicates
						.toArray(new Predicate[predicates.size()]))));
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> remove(Collection<String> input, Predicate... predicates) {
		return CollectionUtils.selectRejected(input, new OrPredicate(BlankPredicate.INSTANCE, new AnyPredicate(
				predicates)));
	}

	@SuppressWarnings("unchecked")
	public static Collection<String> select(Collection<String> input, Predicate... predicates) {
		return CollectionUtils
				.select(input, new AndPredicate(NotBlankPredicate.INSTANCE, new AnyPredicate(predicates)));
	}
}
