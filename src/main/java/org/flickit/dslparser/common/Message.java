package org.flickit.dslparser.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {

    public static final String PARSE_KIT_UNEXPECTED_ERROR_MESSAGE = "Unexpected error in parsing dsl to assessment kit";
    public static final String PARSE_KIT_SYNTAX_ERROR_MESSAGE = "DSL has syntax error";
    public static final String NOT_FOUND_FILE_NAME_MESSAGE = "NOT_DETERMINED";
}
