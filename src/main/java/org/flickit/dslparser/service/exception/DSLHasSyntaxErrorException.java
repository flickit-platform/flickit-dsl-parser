package org.flickit.dslparser.service.exception;

import lombok.Getter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;

@Getter
public class DSLHasSyntaxErrorException extends RuntimeException {

    private final transient EList<Resource.Diagnostic> errors;
    private final String dslContent;

    public DSLHasSyntaxErrorException(String message, EList<Resource.Diagnostic> errors, String dslContent) {
        super(message);
        this.errors = errors;
        this.dslContent = dslContent;
    }
}
