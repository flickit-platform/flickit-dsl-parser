package org.flickit.dslparser.model.assessmentkit;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectModel extends BaseAssessmentModel{
    private List<String> questionnaireCodes;
}