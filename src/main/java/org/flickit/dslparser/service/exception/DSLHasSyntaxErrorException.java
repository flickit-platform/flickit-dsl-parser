package org.flickit.dslparser.service.exception;

import lombok.Getter;
import org.eclipse.xtext.validation.Issue;
import org.flickit.dslparser.controller.exception.api.SyntaxError;

import java.util.List;

@Getter
public class DSLHasSyntaxErrorException extends RuntimeException {

    private final transient List<Issue> issues;
    private final transient List<SyntaxError> syntaxErrors;
    private final String dslContent;

    public DSLHasSyntaxErrorException(String message, List<Issue> issues, List<SyntaxError> syntaxErrors, String dslContent) {
        super(message);
        this.issues = issues;
        this.syntaxErrors = syntaxErrors;
        this.dslContent = dslContent;
    }
}
