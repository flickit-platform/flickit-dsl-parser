package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.AnswerRange;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dslparser.model.assessmentkit.AnswerModel;
import org.flickit.dslparser.model.assessmentkit.AnswerRangeModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
@Slf4j
public class AnswerRangeExtractor implements BaseInfoExtractor<AnswerRangeModel, AnswerRange> {

    public static final int MIN_OPTION_NUMBER = 2;
    public static final int MAX_OPTION_NUMBER = 5;

    protected static final List<Double> DEFAULT_2_OPTION_VALUES = Arrays.asList(0d, 1d);
    protected static final List<Double> DEFAULT_3_OPTION_VALUES = Arrays.asList(0d, 0.5d, 1d);
    protected static final List<Double> DEFAULT_4_OPTION_VALUES = Arrays.asList(0d, 0.5d, 0.7d, 1.0d);
    protected static final List<Double> DEFAULT_5_OPTION_VALUES = Arrays.asList(0d, 0.1d, 0.5d, 0.9d, 1d);

    @Override
    public AnswerRangeModel extract(AnswerRange answerRange) {
        AnswerRangeModel answerRangeModel = new AnswerRangeModel();
        answerRangeModel.setCode(answerRange.getName());
        answerRangeModel.setTitle(answerRange.getTitle());

        var options = answerRange.getOptions();
        var optionCounts = options.size();
        validateOptionsSize(optionCounts);
        for (int i = 0; i < optionCounts; i++) {
            AnswerModel answerModel = new AnswerModel();
            answerModel.setCaption(options.get(i));
            int index = i + 1;
            answerModel.setIndex(index);

            if (answerRange.getValues() == null || answerRange.getValues().isEmpty())
                answerModel.setValue(getDefaultOptionValue(optionCounts, i));
            else
                answerModel.setValue(Double.valueOf(answerRange.getValues().get(i)));

            answerRangeModel.addToOptions(answerModel);
        }

        return answerRangeModel;
    }

    private void validateOptionsSize(int optionNumber) {
        if (optionNumber < MIN_OPTION_NUMBER || optionNumber > MAX_OPTION_NUMBER) {
            log.error("The number of options for question's answer range is invalid");
            throw new RuntimeException("The number of options is invalid");
        }
    }

    public static Double getDefaultOptionValue(int optionNumber, int optionIndex) {
        if (optionIndex >= optionNumber)
            throw new IllegalArgumentException("Invalid option number.");
        return getDefaultOptionValue(optionNumber).get(optionIndex);
    }

    private static List<Double> getDefaultOptionValue(int optionNumber) {
        return switch (optionNumber) {
            case 2 -> DEFAULT_2_OPTION_VALUES;
            case 3 -> DEFAULT_3_OPTION_VALUES;
            case 4 -> DEFAULT_4_OPTION_VALUES;
            case 5 -> DEFAULT_5_OPTION_VALUES;
            default -> throw new IllegalArgumentException("Invalid option number.");
        };
    }

    @Override
    public List<AnswerRangeModel> extractList(EList<BaseInfo> elements) {
        XtextModel<AnswerRange> xtextModel = extractModel(elements);
        List<AnswerRange> xtextAnswerRanges = xtextModel.getModels();
        List<AnswerRangeModel> answerRangeModels = new ArrayList<>();
        for (AnswerRange xtextAnswerRange : xtextAnswerRanges) {
            AnswerRangeModel answerRangeModel = extract(xtextAnswerRange);
            answerRangeModels.add(answerRangeModel);
        }
        return answerRangeModels;
    }

    @Override
    public XtextModel<AnswerRange> extractModel(EList<BaseInfo> elements) {
        XtextModel<AnswerRange> xtextModel = new XtextModel<>();
        List<AnswerRange> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (AnswerRange.class.isAssignableFrom(element.getClass())) {
                AnswerRange model = (AnswerRange) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }
}
