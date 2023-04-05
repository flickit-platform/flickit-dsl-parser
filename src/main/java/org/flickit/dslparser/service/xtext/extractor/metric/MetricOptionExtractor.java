package org.flickit.dslparser.service.xtext.extractor.metric;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.flickit.dslparser.model.profile.AnswerModel;
import org.flickit.dslparser.model.profile.MetricModel;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MetricOptionExtractor {

    public static final int MIN_OPTION_NUMBER = 2;
    public static final int MAX_OPTION_NUMBER = 5;

    public void setupMetricOptions(MetricModel metricModel, EList<String> options) {
        int optionNumber = options.size();
        validateOptions(optionNumber);
        for (int i = 0; i < optionNumber; i++) {
            AnswerModel answerModel = new AnswerModel();
            answerModel.setCaption(options.get(i));
            int index = i+1;
            answerModel.setIndex(index);
            answerModel.setValue(OptionValue.getOptionValue(OptionIndex.getOptionIndex(optionNumber, i)).getValue());
            metricModel.addToAnswers(answerModel);
        }
    }

    private void validateOptions(int optionNumber) {
        if (optionNumber < MIN_OPTION_NUMBER || optionNumber > MAX_OPTION_NUMBER) {
            log.error("The number of option for metric is invalid");
            throw new RuntimeException("The number of option is invalid");
        }
    }

}
