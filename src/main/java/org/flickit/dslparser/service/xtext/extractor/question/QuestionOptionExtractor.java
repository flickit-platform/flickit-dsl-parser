package org.flickit.dslparser.service.xtext.extractor.question;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.flickit.dslparser.model.assessmentkit.AnswerModel;
import org.flickit.dslparser.model.assessmentkit.QuestionModel;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuestionOptionExtractor {

    public static final int MIN_OPTION_NUMBER = 2;
    public static final int MAX_OPTION_NUMBER = 5;

    public void setupQuestionOptions(QuestionModel questionModel, EList<String> options) {
        int optionNumber = options.size();
        validateOptions(optionNumber);
        for (int i = 0; i < optionNumber; i++) {
            AnswerModel answerModel = new AnswerModel();
            answerModel.setCaption(options.get(i));
            int index = i + 1;
            answerModel.setIndex(index);
            answerModel.setValue(QuestionImpactExtractor.getDefaultImpact(optionNumber, i));
            questionModel.addToAnswers(answerModel);
        }
    }

    private void validateOptions(int optionNumber) {
        if (optionNumber < MIN_OPTION_NUMBER || optionNumber > MAX_OPTION_NUMBER) {
            log.error("The number of option for question is invalid");
            throw new RuntimeException("The number of option is invalid");
        }
    }
}
