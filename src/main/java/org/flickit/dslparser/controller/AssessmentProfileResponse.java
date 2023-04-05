package org.flickit.dslparser.controller;

import org.flickit.dslparser.model.profile.*;
import lombok.Data;

import java.util.List;

@Data
public class AssessmentProfileResponse {
    private List<QuestionnaireModel> questionnaireModels;
    private List<AttributeModel> attributeModels;
    private List<MetricModel> metricModels;
    private List<SubjectModel> subjectModels;

    private boolean hasError;
}
