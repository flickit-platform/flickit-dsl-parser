package org.flickit.dslparser.service.xtext.extractor.question;

import org.flickit.dsl.editor.v2.assessmentKitDsl.AffectsAttribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Attribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.OnLevel;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Question;
import org.flickit.dslparser.model.assessmentkit.AnswerModel;
import org.flickit.dslparser.model.assessmentkit.ImpactModel;
import org.flickit.dslparser.model.assessmentkit.LevelModel;
import org.flickit.dslparser.model.assessmentkit.QuestionModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionImpactExtractor {

    public void setupQuestionImpacts(QuestionModel questionModel, Question question) {
        for (AffectsAttribute affects : question.getAffectsAttribute()) {
            Attribute attribute = affects.getAttribute();
            for (OnLevel onLevel : affects.getOnLevel()) {
                ImpactModel impactModel = new ImpactModel();
                LevelModel levelModel = new LevelModel();
                levelModel.setCode(onLevel.getLevel().getName());
                levelModel.setTitle(onLevel.getLevel().getTitle());
                impactModel.setLevel(levelModel);
                impactModel.setAttributeCode(attribute.getName());
                impactModel.setWeight(onLevel.getWeight() != 0 ? onLevel.getWeight() : 1);
                extractQuestionImpacts(impactModel, questionModel, question);
            }
        }
    }

    private void extractQuestionImpacts(ImpactModel impactModel, QuestionModel questionModel, Question question) {
        Map<Integer, Double> optionValueMap = new HashMap<>();
        List<Double> values;
        if (questionModel.getAnswers() != null)
            values = questionModel.getAnswers().stream().map(AnswerModel::getValue).toList();
        else
            values = question.getAnswerRange().getValues().stream().map(Double::valueOf).toList();

        int j = 1;
        for (Double value : values)
            optionValueMap.put(j++, value);

        impactModel.setOptionValues(optionValueMap);
        questionModel.addToImpacts(impactModel);
    }
}
