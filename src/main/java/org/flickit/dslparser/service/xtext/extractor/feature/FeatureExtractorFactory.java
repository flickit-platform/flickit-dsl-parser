package org.flickit.dslparser.service.xtext.extractor.feature;

import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatureExtractorFactory {

    @Autowired
    StringFeatureExtractor stringFeatureExtractor;

    @Autowired
    NumberFeatureExtractor numberFeatureExtractor;

    @Autowired
    SubjectFeatureExtractor subjectFeatureExtractor;

    @Autowired
    QuestionnaireFeatureExtractor questionnaireFeatureExtractor;

    @Autowired
    QuestionnaireFeatureListExtractor questionnaireFeatureListExtractor;

    @Autowired
    ImpactLevelExtractor impactLevelExtractor;

    @Autowired
    AnswerFeatureExtractor answerFeatureExtractor;

    @Autowired
    MetricSimpleFeatureExtractor metricSimpleFeatureExtractor;



    public FeatureExtractor getExtractor(EObject eObject) {
        if (eObject  instanceof StringValue) {
            return this.stringFeatureExtractor;
        }
        if (eObject  instanceof IntValue) {
            return this.numberFeatureExtractor;
        }
        if (eObject  instanceof SubjectFeature) {
            return this.subjectFeatureExtractor;
        }
        if (eObject  instanceof QuestionnairesFeature) {
            return this.questionnaireFeatureListExtractor;
        }
        if (eObject  instanceof QuestionnaireFeature) {
            return this.questionnaireFeatureExtractor;
        }
        if (eObject  instanceof ImpactLevel1 || eObject  instanceof ImpactLevel2 || eObject  instanceof ImpactLevel3) {
            return this.impactLevelExtractor;
        }
        if (eObject  instanceof Answer) {
            return this.answerFeatureExtractor;
        }
        if (eObject  instanceof MetricSimpleFeature) {
            return this.metricSimpleFeatureExtractor;
        }
        return null;
    }
}
