package org.flickit.dslparser.model.profile;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MetricModel extends BaseAssessmentModel{
    private String question;
    private String questionnaireCode;
    private List<ImpactModel> metricImpacts;

    private List<AnswerModel> answers;

    public void addToImpacts(ImpactModel impactModel) {
        if(getMetricImpacts() == null) {
            setMetricImpacts(new ArrayList<>());
        }
        getMetricImpacts().add(impactModel);
    }

    public void addToAnswers(AnswerModel answer) {
        if(getAnswers() == null) {
            setAnswers(new ArrayList<>());
        }
        getAnswers().add(answer);
    }

}
