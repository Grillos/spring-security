package com.spring.security.util;

import java.util.function.Predicate;

public class CommonUtil {

    public static Boolean isNullOrEmpty(String value) {
        Predicate<String> result = v -> v.isEmpty();
        return result.test(value);
    }
}
