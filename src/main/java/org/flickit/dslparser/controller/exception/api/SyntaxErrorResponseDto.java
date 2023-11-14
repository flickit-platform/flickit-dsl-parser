package org.flickit.dslparser.controller.exception.api;

import lombok.Value;

import java.util.List;

@Value
public class SyntaxErrorResponseDto {

    String message;
    List<SyntaxError> errors;
}
