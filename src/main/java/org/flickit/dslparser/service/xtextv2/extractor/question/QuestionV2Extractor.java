package org.flickit.dslparser.service.xtextv2.extractor.question;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Question;
import org.flickit.dslparser.model.assessmentkit.QuestionModel;
import org.flickit.dslparser.model.xtext.XtextV2Model;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.BaseInfoExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class QuestionV2Extractor implements BaseInfoExtractor<QuestionModel, Question> {

    @Autowired
    QuestionOptionV2Extractor questionOptionExtractor;

    @Autowired
    QuestionImpactV2Extractor questionImpactExtractor;

    private static void setupQuestionIndex(List<QuestionModel> questionModels) {
        Map<String, List<QuestionModel>> questionByCategoryMap = questionModels.stream().collect(Collectors.groupingBy(QuestionModel::getQuestionnaireCode));
        for (List<QuestionModel> models : questionByCategoryMap.values()) {
            int index = 1;
            for (QuestionModel model : models) {
                model.setIndex(index);
                index++;
            }
        }
    }

    @Override
    public QuestionModel extract(Question question) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setQuestionnaireCode(question.getQuestionnaire().getName());
        questionModel.setDescription(question.getGuide());
        questionModel.setTitle(question.getTitle());
        questionModel.setMayNotBeApplicable(Boolean.valueOf(question.getMayNotBeApplicable()));
        questionOptionExtractor.setupQuestionOptions(questionModel, question.getOptions());
        questionImpactExtractor.setupQuestionImpacts(questionModel, question);
        return questionModel;
    }

    @Override
    public List<QuestionModel> extractList(EList<BaseInfo> elements) {
        XtextV2Model<Question> XtextV2Model = extractModel(elements);
        List<Question> xtextQuestions = XtextV2Model.getModels();
        List<QuestionModel> questionModels = new ArrayList<>();
        for (int i = 0; i < xtextQuestions.size(); i++) {
            QuestionModel questionModel = extract(xtextQuestions.get(i));
            questionModels.add(questionModel);
        }
        setupQuestionIndex(questionModels);
        return questionModels;
    }

    @Override
    public XtextV2Model<Question> extractModel(EList<BaseInfo> elements) {
        XtextV2Model<Question> XtextV2Model = new XtextV2Model<>();
        List<Question> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Question.class.isAssignableFrom(element.getClass())) {
                Question model = (Question) element;
                models.add(model);
            }
        }
        XtextV2Model.setModels(models);
        return XtextV2Model;
    }
}
