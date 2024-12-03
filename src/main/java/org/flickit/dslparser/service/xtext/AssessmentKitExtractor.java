package org.flickit.dslparser.service.xtext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.impl.RootImpl;
import org.flickit.dslparser.common.Message;
import org.flickit.dslparser.controller.AssessmentKitResponse;
import org.flickit.dslparser.model.assessmentkit.*;
import org.flickit.dslparser.service.exception.DSLHasSyntaxErrorException;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.*;
import org.flickit.dslparser.service.xtext.extractor.question.QuestionExtractor;
import org.flickit.dslparser.service.xtext.validator.ParserValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssessmentKitExtractor {

    private final ResourceServiceImpl resourceService;
    private final SubjectExtractor subjectExtractor;
    private final AttributeExtractor attributeExtractor;
    private final QuestionnaireExtractor questionnaireExtractor;
    private final AnswerRangeExtractor answerRangeExtractor;
    private final QuestionExtractor questionExtractor;
    private final LevelExtractor levelExtractor;
    private final IResourceValidator xTextValidator;
    private final ParserValidator parserValidator;

    public AssessmentKitResponse extract(String dslContent) {
        try {
            Resource resource = resourceService.setupResource(dslContent);
            validateKit(dslContent, resource);
            RootImpl assessmentKit = (RootImpl) resource.getContents().get(0);
            return convert(assessmentKit);
        } catch (DSLHasSyntaxErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            AssessmentKitResponse response = new AssessmentKitResponse(true);
            log.error(Message.PARSE_KIT_UNEXPECTED_ERROR_MESSAGE, ex);
            return response;
        }
    }

    private void validateKit(String dslContent, Resource resource) {
        var issues = xTextValidator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        var syntaxErrors = parserValidator.validate((RootImpl) resource.getContents().get(0));
        if (issues.stream().anyMatch(i -> i.getSeverity().equals(Severity.ERROR)) || !syntaxErrors.isEmpty()) {
            log.debug("DSL has {} syntax error", issues.size() + syntaxErrors.size());
            throw new DSLHasSyntaxErrorException(Message.PARSE_KIT_SYNTAX_ERROR_MESSAGE, issues, syntaxErrors, dslContent);
        }
    }

    private AssessmentKitResponse convert(RootImpl assessmentKit) {
        EList<BaseInfo> elements = assessmentKit.getElements();
        List<QuestionnaireModel> questionnaireModels = questionnaireExtractor.extractList(elements);
        List<SubjectModel> subjectModels = subjectExtractor.extractList(elements);
        List<AttributeModel> attributeModels = attributeExtractor.extractList(elements);
        List<AnswerRangeModel> answerRangeModels = answerRangeExtractor.extractList(elements);
        List<QuestionModel> questionModels = questionExtractor.extractList(elements);
        List<LevelModel> levelModels = levelExtractor.extractList(elements);

        return new AssessmentKitResponse(questionnaireModels,
                attributeModels,
                answerRangeModels,
                questionModels,
                subjectModels,
                levelModels,
                false);
    }
}
