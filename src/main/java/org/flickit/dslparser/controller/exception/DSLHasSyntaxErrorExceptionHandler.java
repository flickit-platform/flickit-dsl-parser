package org.flickit.dslparser.controller.exception;

import lombok.RequiredArgsConstructor;
import org.flickit.dslparser.controller.exception.api.SyntaxErrorResponseDto;
import org.flickit.dslparser.service.exception.DSLHasSyntaxErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.flickit.dslparser.controller.exception.api.ErrorCodes.SYNTAX_ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
public class DSLHasSyntaxErrorExceptionHandler {

    private final DSLHasSyntaxErrorExceptionHandlerHelper helper;

    @ResponseBody
    @ExceptionHandler(DSLHasSyntaxErrorException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    SyntaxErrorResponseDto handle(DSLHasSyntaxErrorException ex) {
        return new SyntaxErrorResponseDto(SYNTAX_ERROR, helper.extractErrors(ex));
    }
}
