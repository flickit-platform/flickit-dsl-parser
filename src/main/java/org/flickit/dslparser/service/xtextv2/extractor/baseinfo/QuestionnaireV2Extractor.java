package org.flickit.dslparser.service.xtextv2.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Questionnaire;
import org.flickit.dslparser.model.assessmentkit.QuestionnaireModel;
import org.flickit.dslparser.model.xtext.XtextV2Model;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class QuestionnaireV2Extractor implements BaseInfoExtractor<QuestionnaireModel, Questionnaire> {

    @Override
    public List<QuestionnaireModel> extractList(EList<BaseInfo> elements) {
        XtextV2Model<Questionnaire> xtextV2Model = extractModel(elements);
        List<Questionnaire> xtextQuestionnaires = xtextV2Model.getModels();
        List<QuestionnaireModel> questionnaireModels = new ArrayList<>();
        for (int i = 0; i < xtextQuestionnaires.size(); i++) {
            QuestionnaireModel questionnaireModel = extract(xtextQuestionnaires.get(i));
            setupIndex(i, questionnaireModel);
            questionnaireModels.add(questionnaireModel);
        }
        return questionnaireModels;
    }

    @Override
    public XtextV2Model<Questionnaire> extractModel(EList<BaseInfo> elements) {
        XtextV2Model<Questionnaire> xtextV2Model = new XtextV2Model<>();
        List<Questionnaire> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Questionnaire.class.isAssignableFrom(element.getClass())) {
                Questionnaire model = (Questionnaire) element;
                models.add(model);
            }
        }
        xtextV2Model.setModels(models);
        return xtextV2Model;
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
