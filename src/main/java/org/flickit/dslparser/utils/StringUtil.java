package org.flickit.dslparser.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static String trimIfNotNull(String str) {
        return str == null ? null : str.trim();
    }
}
