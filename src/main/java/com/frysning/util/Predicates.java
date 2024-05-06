package com.frysning.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Predicates {
	public static <T> Predicate<T> distinctBy(Function<? super T, ?> f) {
		Set<Object> objects = new HashSet<>();
		return r -> objects.add(f.apply(r));
	}
}
