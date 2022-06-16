package com.adidas.subscription.utils;

import java.util.Collection;

public class Helper {

	public static <T> boolean anyNull(Collection<T> c) {
		return c != null && c.stream().anyMatch(o -> o == null);
	}
}
