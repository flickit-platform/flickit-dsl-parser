package org.flickit.dslparser.service.xtext.extractor.feature;

import org.flickit.dslparser.model.profile.BaseAssessmentModel;
import org.flickit.dslparser.model.profile.QuestionnaireModel;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.QuestionnaireExtractor;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.Questionnaire;
import org.flickit.dsl.editor.profile.QuestionnaireFeature;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QuestionnaireFeatureExtractor implements FeatureExtractor {

    @Autowired
    @Qualifier("questionnaire")
    QuestionnaireExtractor questionnaireExtractor;

    @Override
    public void extract(EObject eObject, BaseAssessmentModel model) {
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(model);
        QuestionnaireModel questionnaireModel = questionnaireExtractor.extract(((QuestionnaireFeature) eObject).getFeatureValue().get(0));
        myAccessor.setPropertyValue("questionnaireCode", questionnaireModel.getCode());

    }

    public QuestionnaireModel extractModel(Questionnaire questionnaire) {
        QuestionnaireModel questionnaireModel = questionnaireExtractor.extract(questionnaire);
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(questionnaireModel);
        myAccessor.setPropertyValue("code", questionnaireModel.getCode());
        return questionnaireModel;
    }

}
