package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.assessmentKitDsl.Questionnaire;
import org.flickit.dslparser.model.assessmentkit.QuestionnaireModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Qualifier("questionnaire")
public class QuestionnaireExtractor implements BaseInfoExtractor<QuestionnaireModel, Questionnaire> {

    @Autowired
    CodeGenerator codeGenerator;
    @Override
    public List<QuestionnaireModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Questionnaire> xtextModel =  extractModel(elements);
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
        for(BaseInfo element : elements) {
            if(Questionnaire.class.isAssignableFrom(element.getClass())) {
                Questionnaire model = (Questionnaire) element;
                model.setCode(codeGenerator.generate());
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

    @Override
    public QuestionnaireModel extract(Questionnaire questionnaire) {
        QuestionnaireModel questionnaireModel = new QuestionnaireModel();
        questionnaireModel.setCode(questionnaire.getCode());
        questionnaireModel.setTitle(questionnaire.getTitle());
        questionnaireModel.setDescription(questionnaire.getDescription());
        return questionnaireModel;
    }

}
