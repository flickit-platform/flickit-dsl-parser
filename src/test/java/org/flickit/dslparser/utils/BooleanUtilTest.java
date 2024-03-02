package org.flickit.dslparser.utils;

import org.junit.jupiter.api.Test;

import static org.flickit.dslparser.utils.BooleanUtil.parseBooleanOrDefaultTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BooleanUtilTest {

    @Test
    void testParseBooleanOrDefaultTrue_valueIsNull_ReturnTrue(){
        assertTrue(parseBooleanOrDefaultTrue(null));
    }

    @Test
    void testParseBooleanOrDefaultTrue_valueIsTrue_ReturnTrue(){
        assertTrue(parseBooleanOrDefaultTrue("true"));
    }

    @Test
    void testParseBooleanOrDefaultTrue_valueIsFalse_ReturnFalse(){
        assertFalse(parseBooleanOrDefaultTrue("false"));
    }
}