package org.flickit.dslparser.model.assessmentkit;

import lombok.Data;

@Data
public class BaseAssessmentModel {
    private String code;
    private String title;
    private String description;
    private Integer index;

}
