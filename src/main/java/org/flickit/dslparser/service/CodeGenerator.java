package org.flickit.dslparser.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class CodeGenerator {

    private long code = 0;

    public String generate() {
        code++;
        return String.valueOf(code);
    }
}
