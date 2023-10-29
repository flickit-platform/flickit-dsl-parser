package org.flickit.dslparser.controller.exception.api;

import lombok.Value;

@Value
public class SyntaxError {

    String message;
    String fileName;
    int line;
    int column;
}
