package org.flickit.dslparser.controller;

import org.flickit.dslparser.model.assessmentkit.*;

import java.util.List;

public record AssessmentKitResponse(List<QuestionnaireModel> questionnaireModels,
                                    List<AttributeModel> attributeModels,
                                    List<QuestionModel> questionModels,
                                    List<SubjectModel> subjectModels,
                                    List<LevelModel> levelModels,
                                    boolean hasError) {

    public AssessmentKitResponse(boolean hasError) {
        this(null, null, null, null, null, hasError);
    }
}
