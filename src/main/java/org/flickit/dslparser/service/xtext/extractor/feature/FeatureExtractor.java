package org.flickit.dslparser.service.xtext.extractor.feature;

import org.flickit.dslparser.model.profile.BaseAssessmentModel;
import org.eclipse.emf.ecore.EObject;

public interface FeatureExtractor {
    void extract(EObject eObject, BaseAssessmentModel model);

}
