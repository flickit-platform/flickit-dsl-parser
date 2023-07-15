package org.flickit.dslparser.service.xtext.extractor.question;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.assessmentKitDsl.Question;

import org.flickit.dslparser.model.assessmentkit.QuestionModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.BaseInfoExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Qualifier("question")
@Slf4j
public class QuestionExtractor implements BaseInfoExtractor<QuestionModel, Question> {

    @Autowired
    QuestionOptionExtractor questionOptionExtractor;

    @Autowired
    QuestionImpactExtractor questionImpactExtractor;

    @Override
    public QuestionModel extract(Question question) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setTitle(question.getQuestion());
        questionModel.setQuestionnaireCode(question.getQuestionnaire().getCode());
        questionModel.setMayNotBeApplicable(Boolean.valueOf(question.getMayNotBeApplicable()));
        questionOptionExtractor.setupQuestionOptions(questionModel, question.getOptions());
        questionImpactExtractor.setupQuestionImpacts(questionModel, question);
        return questionModel;
    }

    @Override
    public List<QuestionModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Question> xtextModel =  extractModel(elements);
        List<Question> xtextQuestions = xtextModel.getModels();
        List<QuestionModel> questionModels = new ArrayList<>();
        for (int i = 0; i < xtextQuestions.size(); i++) {
            QuestionModel questionModel = extract(xtextQuestions.get(i));
            questionModels.add(questionModel);
        }
        setupQuestionIndex(questionModels);
        return questionModels;
    }

    private static void setupQuestionIndex(List<QuestionModel> questionModels) {
        Map<String, List<QuestionModel>> questionByCategoryMap = questionModels.stream().collect(Collectors.groupingBy(QuestionModel::getQuestionnaireCode));
        for(List<QuestionModel> models: questionByCategoryMap.values()) {
            int index = 1;
            for(QuestionModel model: models) {
                model.setIndex(index);
                index++;
            }
        }
    }

    @Override
    public XtextModel<Question> extractModel(EList<BaseInfo> elements) {
        XtextModel<Question> xtextModel = new XtextModel<>();
        List<Question> models = new ArrayList<>();
        for(BaseInfo element : elements) {
            if(Question.class.isAssignableFrom(element.getClass())) {
                Question model = (Question) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

}
