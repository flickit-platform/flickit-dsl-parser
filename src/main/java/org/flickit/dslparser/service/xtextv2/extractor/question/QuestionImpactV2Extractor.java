package org.flickit.dslparser.service.xtextv2.extractor.question;

import org.flickit.dsl.editor.v2.assessmentKitDsl.AffectsAttribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Attribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.OnLevel;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Question;
import org.flickit.dslparser.model.assessmentkit.ImpactModel;
import org.flickit.dslparser.model.assessmentkit.LevelModel;
import org.flickit.dslparser.model.assessmentkit.QuestionModel;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionImpactV2Extractor {

    protected static final List<Double> DEFAULT_2_OPTION_VALUES = Arrays.asList(0d, 1d);
    protected static final List<Double> DEFAULT_3_OPTION_VALUES = Arrays.asList(0d, 0.5d, 1d);
    protected static final List<Double> DEFAULT_4_OPTION_VALUES = Arrays.asList(0d, 0.5d, 0.7d, 1.0d);
    protected static final List<Double> DEFAULT_5_OPTION_VALUES = Arrays.asList(0d, 0.2d, 0.5d, 0.9d, 1d);

    public void setupQuestionImpacts(QuestionModel questionModel, Question question) {
        for (AffectsAttribute affects : question.getAffectsAttribute()) {
            Attribute attribute = affects.getAttribute();
            for (OnLevel onLevel : affects.getOnLevel()) {
                ImpactModel impactModel = new ImpactModel();
                LevelModel levelModel = new LevelModel();
                levelModel.setTitle(onLevel.getLevel().getTitle());
                impactModel.setLevel(levelModel);
                impactModel.setAttributeCode(attribute.getName());
                impactModel.setWeight(onLevel.getWeight() != 0 ? onLevel.getWeight() : 1);
                extractQuestionImpacts(onLevel, impactModel, questionModel);
            }
        }
    }

    private void extractQuestionImpacts(OnLevel onLevel, ImpactModel impactModel, QuestionModel questionModel) {
        Map<Integer, Double> optionValueMap = new HashMap<>();
        List<Double> values;
        if (onLevel.getValues() == null || onLevel.getValues().isEmpty()) {
            values = getDefaultImpact(questionModel.getAnswers().size());
        } else {
            values = onLevel.getValues().stream().map(Double::valueOf).toList();
        }
        int j = 1;
        for (Double value : values) {
            optionValueMap.put(j++, value);
        }
        impactModel.setOptionValues(optionValueMap);
        questionModel.addToImpacts(impactModel);
    }

    private List<Double> getDefaultImpact(int optionNumber) {
        return switch (optionNumber) {
            case 2 -> DEFAULT_2_OPTION_VALUES;
            case 3 -> DEFAULT_3_OPTION_VALUES;
            case 4 -> DEFAULT_4_OPTION_VALUES;
            case 5 -> DEFAULT_5_OPTION_VALUES;
            default -> throw new IllegalArgumentException("Invalid option number.");
        };
    }
}
