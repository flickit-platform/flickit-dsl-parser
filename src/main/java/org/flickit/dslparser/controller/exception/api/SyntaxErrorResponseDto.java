package org.flickit.dslparser.controller.exception.api;

import java.util.List;

public record SyntaxErrorResponseDto(String message, List<SyntaxError> errors) {
}
