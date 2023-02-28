package org.flickit.dslparser.service.xtext.extractor.feature;

import org.flickit.dslparser.model.profile.AnswerModel;
import org.flickit.dslparser.model.profile.BaseAssessmentModel;
import org.flickit.dslparser.model.profile.MetricModel;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.impl.AnswerImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
@Slf4j
public class AnswerFeatureExtractor implements FeatureExtractor {
    @Override
    public void extract(EObject eObject, BaseAssessmentModel model) {
        EList<String> answers = ((AnswerImpl) eObject).getFeatureValue();
        int optionNumber = answers.size();
        validateOptions(optionNumber);
        for(int i=0; i < optionNumber; i++) {
            AnswerModel answer = new AnswerModel();
            answer.setCaption(answers.get(i));
            if(answer.getIndex() == null) {
                int index = i+1;
                answer.setIndex(index);
            }
            setupAnswers(optionNumber, i, answer);
            addAnswerToMetricModel(model, answer);
        }
    }

    private static void setupAnswers(int optionNumber, int i, AnswerModel answer) {

        if (optionNumber == 2) {
            if (i == 0) {
                answer.setValue(1);
            } else {
                answer.setValue(5);
            }
        }

        if(optionNumber == 3) {
            if(i == 0) {
                answer.setValue(1);
            } else if (i == 1) {
                answer.setValue(3);
            } else {
                answer.setValue(5);
            }
        }
        if(optionNumber == 4) {
            if(i == 0) {
                answer.setValue(1);
            } else if (i == 1) {
                answer.setValue(2);
            } else if (i == 2) {
                answer.setValue(4);
            } else {
                answer.setValue(5);
            }
        }
        if(optionNumber == 5) {
            if(i == 0) {
                answer.setValue(1);
            } else if (i == 1) {
                answer.setValue(2);
            } else if (i == 2) {
                answer.setValue(3);
            } else if (i == 3) {
                answer.setValue(4);
            } else {
                answer.setValue(5);
            }
        }
    }

    private static void validateOptions(int optionNumber) {
        if(optionNumber < 2 || optionNumber > 5) {
            log.error("The number of option for metric is invalid");
            throw new RuntimeException("The number of option  is invalid");
        }
    }

    private void addAnswerToMetricModel(BaseAssessmentModel model, AnswerModel answerModel) {
        try {
            Method addToImpactsMethod = MetricModel.class.getMethod("addToAnswers", AnswerModel.class);
            addToImpactsMethod.invoke(model, answerModel);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
