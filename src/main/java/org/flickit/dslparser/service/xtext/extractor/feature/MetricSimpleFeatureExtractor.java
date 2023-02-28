package org.flickit.dslparser.service.xtext.extractor.feature;

import org.flickit.dslparser.model.profile.BaseAssessmentModel;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.MetricSimpleFeature;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

@Component
public class MetricSimpleFeatureExtractor implements FeatureExtractor {
    @Override
    public void extract(EObject eObject, BaseAssessmentModel model) {
        MetricSimpleFeature stringProp = (MetricSimpleFeature) eObject;
        if(stringProp.getName().equals("question")) {
            PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(model);
            myAccessor.setPropertyValue("question", stringProp.getFeatureValue());
        }
    }
}
