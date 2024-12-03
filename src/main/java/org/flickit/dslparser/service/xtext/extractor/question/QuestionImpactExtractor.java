package org.flickit.dslparser.service.xtext.extractor.question;

import org.flickit.dsl.editor.v2.assessmentKitDsl.AffectsAttribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Attribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.OnLevel;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Question;
import org.flickit.dslparser.model.assessmentkit.ImpactModel;
import org.flickit.dslparser.model.assessmentkit.LevelModel;
import org.flickit.dslparser.model.assessmentkit.QuestionModel;
import org.springframework.stereotype.Component;

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
                questionModel.addToImpacts(impactModel);
            }
        }
    }
}
