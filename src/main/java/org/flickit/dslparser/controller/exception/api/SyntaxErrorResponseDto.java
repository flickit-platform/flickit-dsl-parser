package org.flickit.dslparser.controller.exception.api;

import lombok.Value;

import java.util.LinkedHashSet;

@Value
public class SyntaxErrorResponseDto {

    String message;
    LinkedHashSet<SyntaxError> errors;
}
