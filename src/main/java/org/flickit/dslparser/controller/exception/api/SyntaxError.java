package org.flickit.dslparser.controller.exception.api;

public record SyntaxError(String message,
                          String fileName,
                          String errorLine,
                          Integer line,
                          Integer column) {

    public SyntaxError(String message) {
        this(message, null, null, null, null);
    }
}
