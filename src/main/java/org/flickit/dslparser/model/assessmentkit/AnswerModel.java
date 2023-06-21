package org.flickit.dslparser.model.assessmentkit;

import lombok.Data;

@Data
public class AnswerModel {
    private String caption;
    private Integer value;
    private Integer index;
}
