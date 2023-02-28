package org.flickit.dslparser.model.profile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionnaireModel extends BaseAssessmentModel {
    private Integer index;
}
