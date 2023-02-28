package org.flickit.dslparser.model.profile;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectModel extends BaseAssessmentModel{
    private Integer index;
    private List<String> questionnaireCodes;
}