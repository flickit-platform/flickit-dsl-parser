package org.flickit.dslparser.model.assessmentkit;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AnswerRangeModel extends BaseAssessmentModel {

    private List<AnswerModel> options;

    public void addToOptions(AnswerModel answer) {
        if(getOptions() == null) {
            setOptions(new ArrayList<>());
        }
        getOptions().add(answer);
    }
}
