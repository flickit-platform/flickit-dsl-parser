package org.flickit.dslparser.service;

import org.checkerframework.checker.units.qual.A;
import org.flickit.dslparser.controller.AssessmentProfileResponse;
import org.flickit.dslparser.model.profile.*;
import org.flickit.dslparser.service.xtext.ResourceService;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.*;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.impl.AssessmentProfileImpl;
import org.flickit.dslparser.service.xtext.extractor.metric.MetricExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AssessmentProfileExtractor {

    @Autowired
    ResourceService resourceService;
    @Autowired
    SubjectExtractor subjectExtractor;

    @Autowired
    AttributeExtractor attributeExtractor;

    @Autowired
    QuestionnaireExtractor questionnaireExtractor;

    @Autowired
    MetricExtractor metricExtractor;

    @Autowired
    LevelExtractor levelExtractor;
    @Autowired
    CodeGenerator codeGenerator;


    public AssessmentProfileResponse extract(String dslContent) {
        Long lastCode = codeGenerator.readLastCodeFromFile();
        try {
            Resource resource = resourceService.setupResource(dslContent);
            AssessmentProfileImpl assessmentProfile = (AssessmentProfileImpl) resource.getContents().get(0);
            return convert(assessmentProfile);
        } catch (Exception ex) {
            AssessmentProfileResponse response = new AssessmentProfileResponse();
            response.setHasError(true);
            codeGenerator.saveNewCodeToFile(String.valueOf(lastCode));
            log.error("Error in parsing dsl to assessment profile", ex);
            return response;
        }


    }

    private AssessmentProfileResponse convert (AssessmentProfileImpl assessmentProfile) {
        EList<BaseInfo> elements = assessmentProfile.getElements();
        List<QuestionnaireModel> questionnaireModels = questionnaireExtractor.extractList(elements);
        List<SubjectModel> subjectModels = subjectExtractor.extractList(elements);
        List<AttributeModel> attributeModels = attributeExtractor.extractList(elements);
        List<MetricModel> metricModels = metricExtractor.extractList(elements);
        List<LevelModel> levelModels = levelExtractor.extractList(elements);

        AssessmentProfileResponse response = new AssessmentProfileResponse();
        response.setSubjectModels(subjectModels);
        response.setAttributeModels(attributeModels);
        response.setQuestionnaireModels(questionnaireModels);
        response.setMetricModels(metricModels);
        response.setLevelModels(levelModels);
        return response;
    }

}
