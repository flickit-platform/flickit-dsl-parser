package org.flickit.dslparser.model.assessmentkit;

import lombok.Data;
import org.flickit.dslparser.utils.StringUtil;

@Data
public class AnswerModel {
    private String caption;
    private Integer value;
    private Integer index;

    public void setCaption(String caption) {
        this.caption = StringUtil.trimIfNotNull(caption);
    }
}
