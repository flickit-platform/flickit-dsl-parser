package org.flickit.dslparser.utils;

public class StringUtil {
    public static String trimIfNotNull(String str) {
        return str == null ? null : str.trim();
    }
}
