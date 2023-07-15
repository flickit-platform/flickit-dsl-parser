package org.flickit.dslparser.model.assessmentkit;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionModel extends BaseAssessmentModel{
//    private String question;
    private String questionnaireCode;
    private List<ImpactModel> questionImpacts;

    private List<AnswerModel> answers;

    private boolean mayNotBeApplicable;

    public void addToImpacts(ImpactModel impactModel) {
        if(getQuestionImpacts() == null) {
            setQuestionImpacts(new ArrayList<>());
        }
        getQuestionImpacts().add(impactModel);
    }

    public void addToAnswers(AnswerModel answer) {
        if(getAnswers() == null) {
            setAnswers(new ArrayList<>());
        }
        getAnswers().add(answer);
    }

}
