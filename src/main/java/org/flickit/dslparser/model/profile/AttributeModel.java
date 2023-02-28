package org.flickit.dslparser.model.profile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AttributeModel extends BaseAssessmentModel {
    private Integer index;
    private String subjectCode;
}
