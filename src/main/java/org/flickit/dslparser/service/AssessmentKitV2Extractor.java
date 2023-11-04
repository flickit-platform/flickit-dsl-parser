package org.flickit.dslparser.service;

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
import org.flickit.dslparser.controller.AssessmentKitResponse;
import org.flickit.dslparser.model.assessmentkit.*;
import org.flickit.dslparser.service.exception.DSLHasSyntaxErrorException;
import org.flickit.dslparser.service.xtextv2.ResourceServiceV2;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.AttributeV2Extractor;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.LevelV2Extractor;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.QuestionnaireV2Extractor;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.SubjectV2Extractor;
import org.flickit.dslparser.service.xtextv2.extractor.question.QuestionV2Extractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AssessmentKitV2Extractor {

    @Autowired
    private ResourceServiceV2 resourceService;

    @Autowired
    private SubjectV2Extractor subjectExtractor;

    @Autowired
    private AttributeV2Extractor attributeExtractor;

    @Autowired
    private QuestionnaireV2Extractor questionnaireExtractor;

    @Autowired
    private QuestionV2Extractor questionExtractor;

    @Autowired
    private LevelV2Extractor levelExtractor;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private IResourceValidator validator;


    public AssessmentKitResponse extract(String dslContent) {
        Long lastCode = codeGenerator.readLastCodeFromFile();
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
            codeGenerator.saveNewCodeToFile(String.valueOf(lastCode));
            log.error("Unexpected error in parsing dsl to assessment kit", ex);
            return response;
        }
    }

    private void validateKit(String dslContent, Resource resource) {
        List<Issue> issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        if (issues.stream().anyMatch(i -> i.getSeverity().equals(Severity.ERROR))) {
            log.debug("DSL has {} syntax error", issues.size());
            throw new DSLHasSyntaxErrorException("DSL has syntax error!", issues, dslContent);
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
