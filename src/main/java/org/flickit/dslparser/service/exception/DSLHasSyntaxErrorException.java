package org.flickit.dslparser.service.exception;

import lombok.Getter;
import org.eclipse.xtext.validation.Issue;

import java.util.List;

@Getter
public class DSLHasSyntaxErrorException extends RuntimeException {

    private final transient List<Issue> errors;
    private final String dslContent;

    public DSLHasSyntaxErrorException(String message, List<Issue> errors, String dslContent) {
        super(message);
        this.errors = errors;
        this.dslContent = dslContent;
    }
}
