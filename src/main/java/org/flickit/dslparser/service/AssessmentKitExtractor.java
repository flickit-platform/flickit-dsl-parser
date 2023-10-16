package org.flickit.dslparser.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.flickit.dsl.editor.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.assessmentKitDsl.impl.RootImpl;
import org.flickit.dslparser.controller.AssessmentKitResponse;
import org.flickit.dslparser.model.assessmentkit.*;
import org.flickit.dslparser.service.xtext.ResourceService;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.AttributeExtractor;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.LevelExtractor;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.QuestionnaireExtractor;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.SubjectExtractor;
import org.flickit.dslparser.service.xtext.extractor.question.QuestionExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AssessmentKitExtractor {

    @Autowired
    ResourceService resourceService;
    @Autowired
    SubjectExtractor subjectExtractor;

    @Autowired
    AttributeExtractor attributeExtractor;

    @Autowired
    QuestionnaireExtractor questionnaireExtractor;

    @Autowired
    QuestionExtractor questionExtractor;

    @Autowired
    LevelExtractor levelExtractor;


    public AssessmentKitResponse extract(String dslContent) {
        try {
            Resource resource = resourceService.setupResource(dslContent);
            RootImpl assessmentKit = (RootImpl) resource.getContents().get(0);
            return convert(assessmentKit);
        } catch (Exception ex) {
            AssessmentKitResponse response = new AssessmentKitResponse();
            response.setHasError(true);
            log.error("Error in parsing dsl to assessment kit", ex);
            return response;
        }


    }

    private AssessmentKitResponse convert (RootImpl assessmentKit) {
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
