package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.flickit.dslparser.model.profile.QuestionnaireModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.xtext.extractor.feature.FeatureExtractor;
import org.flickit.dslparser.service.xtext.extractor.feature.FeatureExtractorFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Qualifier("questionnaire")
public class QuestionnaireExtractor implements BaseInfoExtractor<QuestionnaireModel, Questionnaire> {

    @Autowired
    FeatureExtractorFactory extractorFactory;

    @Override
    public QuestionnaireModel extract(Questionnaire questionnaire) {
        EList<EObject> features = questionnaire.getFeatures();
        QuestionnaireModel questionnaireModel = new QuestionnaireModel();
        for(EObject eObject: features) {
            FeatureExtractor featureExtractor = extractorFactory.getExtractor(eObject);
            featureExtractor.extract(eObject, questionnaireModel);
        }
        return questionnaireModel;
    }

    @Override
    public List<QuestionnaireModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Questionnaire> xtextModel =  extractModel(elements);
        List<Questionnaire> xtextQuestionnaires = xtextModel.getModels();
        List<QuestionnaireModel> questionnaireModels = new ArrayList<>();
        for (int i = 0; i < xtextQuestionnaires.size(); i++) {
            QuestionnaireModel questionnaireModel = extract(xtextQuestionnaires.get(i));
            if(questionnaireModel.getIndex() == null) {
                int index = i+1;
                questionnaireModel.setIndex(index);
            }
            questionnaireModels.add(questionnaireModel);
        }
        return questionnaireModels;
    }

    @Override
    public XtextModel<Questionnaire> extractModel(EList<BaseInfo> elements) {
        XtextModel<Questionnaire> xtextModel = new XtextModel<>();
        List<Questionnaire> models = new ArrayList<>();
        for(BaseInfo element : elements) {
            if(Questionnaire.class.isAssignableFrom(element.getClass())) {
                Questionnaire model = (Questionnaire) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }
}
