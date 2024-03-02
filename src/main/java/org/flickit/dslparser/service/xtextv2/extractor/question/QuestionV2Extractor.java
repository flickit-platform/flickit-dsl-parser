package org.flickit.dslparser.service.xtextv2.extractor.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Question;
import org.flickit.dslparser.model.assessmentkit.QuestionModel;
import org.flickit.dslparser.model.xtext.XtextV2Model;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.BaseInfoExtractor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Boolean.parseBoolean;
import static org.flickit.dslparser.utils.BooleanUtil.parseBooleanOrDefaultTrue;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuestionV2Extractor implements BaseInfoExtractor<QuestionModel, Question> {

    private final QuestionOptionV2Extractor questionOptionExtractor;
    private final QuestionImpactV2Extractor questionImpactExtractor;

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
        questionModel.setCode(question.getName());
        questionModel.setQuestionnaireCode(question.getQuestionnaire().getName());
        questionModel.setDescription(question.getHint());
        questionModel.setTitle(question.getTitle());
        questionModel.setMayNotBeApplicable(parseBoolean(question.getMayNotBeApplicable()));
        questionModel.setAdvisable(parseBooleanOrDefaultTrue(question.getAdvisable()));
        questionOptionExtractor.setupQuestionOptions(questionModel, question.getOptions());
        questionImpactExtractor.setupQuestionImpacts(questionModel, question);
        return questionModel;
    }

    @Override
    public List<QuestionModel> extractList(EList<BaseInfo> elements) {
        XtextV2Model<Question> xtextV2Model = extractModel(elements);
        List<Question> xtextQuestions = xtextV2Model.getModels();
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
        XtextV2Model<Question> xtextV2Model = new XtextV2Model<>();
        List<Question> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Question.class.isAssignableFrom(element.getClass())) {
                Question model = (Question) element;
                models.add(model);
            }
        }
        xtextV2Model.setModels(models);
        return xtextV2Model;
    }
}
