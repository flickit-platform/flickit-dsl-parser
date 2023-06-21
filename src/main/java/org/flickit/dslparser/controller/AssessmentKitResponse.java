package org.flickit.dslparser.controller;

import org.flickit.dslparser.model.assessmentkit.*;
import lombok.Data;

import java.util.List;

@Data
public class AssessmentKitResponse {
    private List<QuestionnaireModel> questionnaireModels;
    private List<AttributeModel> attributeModels;
    private List<QuestionModel> questionModels;
    private List<SubjectModel> subjectModels;

    private List<LevelModel> levelModels;

    private boolean hasError;
}
