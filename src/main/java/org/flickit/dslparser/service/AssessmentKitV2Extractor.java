package org.flickit.dslparser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.impl.RootImpl;
import org.flickit.dslparser.common.Message;
import org.flickit.dslparser.controller.AssessmentKitResponse;
import org.flickit.dslparser.model.assessmentkit.*;
import org.flickit.dslparser.service.exception.DSLHasSyntaxErrorException;
import org.flickit.dslparser.service.xtextv2.ResourceServiceV2;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.AttributeV2Extractor;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.LevelV2Extractor;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.QuestionnaireV2Extractor;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.SubjectV2Extractor;
import org.flickit.dslparser.service.xtextv2.extractor.question.QuestionV2Extractor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssessmentKitV2Extractor {

    private final ResourceServiceV2 resourceService;
    private final SubjectV2Extractor subjectExtractor;
    private final AttributeV2Extractor attributeExtractor;
    private final QuestionnaireV2Extractor questionnaireExtractor;
    private final QuestionV2Extractor questionExtractor;
    private final LevelV2Extractor levelExtractor;
    private final IResourceValidator validator;

    public AssessmentKitResponse extract(String dslContent) {
        try {
            Resource resource = resourceService.setupResource(dslContent);
            validateKit(dslContent, resource);
            RootImpl assessmentKit = (RootImpl) resource.getContents().get(0);
            return convert(assessmentKit);
        } catch (Exception ex) {
            if (ex instanceof DSLHasSyntaxErrorException)
                throw ex;
            AssessmentKitResponse response = new AssessmentKitResponse();
            response.setHasError(true);
            log.error(Message.PARSE_KIT_UNEXPECTED_ERROR_MESSAGE, ex);
            return response;
        }
    }

    private void validateKit(String dslContent, Resource resource) {
        List<Issue> issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        if (issues.stream().anyMatch(i -> i.getSeverity().equals(Severity.ERROR))) {
            log.debug("DSL has {} syntax error", issues.size());
            throw new DSLHasSyntaxErrorException(Message.PARSE_KIT_SYNTAX_ERROR_MESSAGE, issues, dslContent);
        }
    }

    private AssessmentKitResponse convert(RootImpl assessmentKit) {
        EList<BaseInfo> elements = assessmentKit.getElements();
        List<QuestionnaireModel> questionnaireModels = questionnaireExtractor.extractList(elements);
        List<SubjectModel> subjectModels = subjectExtractor.extractList(elements);
        List<AttributeModel> attributeModels = attributeExtractor.extractList(elements);
        List<QuestionModel> questionModels = questionExtractor.extractList(elements);
        List<LevelModel> levelModels = levelExtractor.extractList(elements);

        AssessmentKitResponse response = new AssessmentKitResponse();
        response.setSubjectModels(subjectModels);
        response.setAttributeModels(attributeModels);
        response.setQuestionnaireModels(questionnaireModels);
        response.setQuestionModels(questionModels);
        response.setLevelModels(levelModels);
        return response;
    }
}
