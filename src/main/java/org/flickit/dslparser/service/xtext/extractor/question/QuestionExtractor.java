package org.flickit.dslparser.service.xtext.extractor.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Question;
import org.flickit.dslparser.model.assessmentkit.QuestionModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.BaseInfoExtractor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.parseBoolean;
import static java.util.stream.Collectors.groupingBy;
import static org.flickit.dslparser.utils.BooleanUtil.parseBooleanOrDefaultTrue;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuestionExtractor implements BaseInfoExtractor<QuestionModel, Question> {

    private final QuestionOptionExtractor questionOptionExtractor;
    private final QuestionImpactExtractor questionImpactExtractor;

    private static void setupQuestionIndex(List<QuestionModel> questionModels) {
        Map<String, List<QuestionModel>> questionByCategoryMap = questionModels.stream()
                .collect(groupingBy(QuestionModel::getQuestionnaireCode));
        for (List<QuestionModel> models : questionByCategoryMap.values()) {
            for (int i = 0; i < models.size(); i++) {
                QuestionModel model = models.get(i);
                model.setIndex(i + 1);
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
        questionModel.setCost(NumberUtils.toInt(question.getCost(), 1));

        if (question.getOptions() != null && !question.getOptions().isEmpty())
            questionOptionExtractor.setupQuestionOptions(questionModel, question.getOptions(), question.getValues());
        else
            questionModel.setAnswerRangeCode(question.getAnswerRange().getName());

        questionImpactExtractor.setupQuestionImpacts(questionModel, question);
        return questionModel;
    }

    @Override
    public List<QuestionModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Question> xtextModel = extractModel(elements);
        List<Question> xtextQuestions = xtextModel.getModels();
        List<QuestionModel> questionModels = new ArrayList<>();
        for (Question xtextQuestion : xtextQuestions) {
            QuestionModel questionModel = extract(xtextQuestion);
            questionModels.add(questionModel);
        }
        setupQuestionIndex(questionModels);
        return questionModels;
    }

    @Override
    public XtextModel<Question> extractModel(EList<BaseInfo> elements) {
        XtextModel<Question> xtextModel = new XtextModel<>();
        List<Question> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Question.class.isAssignableFrom(element.getClass())) {
                Question model = (Question) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }
}
