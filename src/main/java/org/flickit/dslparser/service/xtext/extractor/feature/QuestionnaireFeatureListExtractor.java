package org.flickit.dslparser.service.xtext.extractor.feature;

import org.flickit.dslparser.model.profile.BaseAssessmentModel;
import org.flickit.dslparser.model.profile.QuestionnaireModel;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.Questionnaire;
import org.flickit.dsl.editor.profile.impl.QuestionnairesFeatureImpl;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionnaireFeatureListExtractor extends QuestionnaireFeatureExtractor {

    @Override
    public void extract(EObject eObject, BaseAssessmentModel model) {
        QuestionnairesFeatureImpl questionnaires = (QuestionnairesFeatureImpl) eObject;
        List<QuestionnaireModel> questionnaireModels = new ArrayList<>();
        for(Questionnaire questionnaire: questionnaires.getFeatureValue()) {
            questionnaireModels.add(super.extractModel(questionnaire));
        }
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(model);
        myAccessor.setPropertyValue("questionnaireCodes", questionnaireModels.stream().map(QuestionnaireModel::getCode).collect(Collectors.toList()));

    }
}
