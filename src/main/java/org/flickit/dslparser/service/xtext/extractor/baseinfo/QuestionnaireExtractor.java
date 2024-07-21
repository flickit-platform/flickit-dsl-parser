package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Questionnaire;
import org.flickit.dslparser.model.assessmentkit.QuestionnaireModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class QuestionnaireExtractor implements BaseInfoExtractor<QuestionnaireModel, Questionnaire> {

    @Override
    public List<QuestionnaireModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Questionnaire> xtextModel = extractModel(elements);
        List<Questionnaire> xtextQuestionnaires = xtextModel.getModels();
        List<QuestionnaireModel> questionnaireModels = new ArrayList<>();
        for (int i = 0; i < xtextQuestionnaires.size(); i++) {
            QuestionnaireModel questionnaireModel = extract(xtextQuestionnaires.get(i));
            setupIndex(i, questionnaireModel);
            questionnaireModels.add(questionnaireModel);
        }
        return questionnaireModels;
    }

    @Override
    public XtextModel<Questionnaire> extractModel(EList<BaseInfo> elements) {
        XtextModel<Questionnaire> xtextModel = new XtextModel<>();
        List<Questionnaire> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Questionnaire.class.isAssignableFrom(element.getClass())) {
                Questionnaire model = (Questionnaire) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

    @Override
    public QuestionnaireModel extract(Questionnaire questionnaire) {
        QuestionnaireModel questionnaireModel = new QuestionnaireModel();
        questionnaireModel.setCode(questionnaire.getName());
        questionnaireModel.setTitle(questionnaire.getTitle());
        questionnaireModel.setDescription(questionnaire.getDescription());
        questionnaireModel.setIndex(questionnaire.getIndex() == 0 ? -1 : questionnaire.getIndex());
        return questionnaireModel;
    }
}
