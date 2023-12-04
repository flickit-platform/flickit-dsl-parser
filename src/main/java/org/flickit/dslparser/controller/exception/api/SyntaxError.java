package org.flickit.dslparser.controller.exception.api;

import lombok.Value;

@Value
public class SyntaxError {

    String message;
    String fileName;
    String errorLine;
    int line;
    int column;
}
