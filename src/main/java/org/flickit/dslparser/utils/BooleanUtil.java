package org.flickit.dslparser.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanUtil {

    public static boolean parseBooleanOrDefaultTrue(String value){
        if (value == null)
            return true;
        return Boolean.parseBoolean(value);
    }
}
