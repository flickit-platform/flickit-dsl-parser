package org.flickit.dslparser.model.assessmentkit;

import lombok.Data;
import org.flickit.dslparser.utils.StringUtil;

@Data
public class BaseAssessmentModel {
    private String code;
    private String title;
    private String description;
    private Integer index;

    public void setCode(String code) {
        this.code = StringUtil.trimIfNotNull(code);
    }

    public void setTitle(String title) {
        this.title = StringUtil.trimIfNotNull(title);
    }

    public void setDescription(String description) {
        this.description = StringUtil.trimIfNotNull(description);
    }
}
