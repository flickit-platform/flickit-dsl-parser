package org.flickit.dslparser.service.xtext.extractor.feature;

import org.flickit.dslparser.model.profile.BaseAssessmentModel;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.IntValue;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

@Component
public class NumberFeatureExtractor implements FeatureExtractor {

    @Override
    public void extract(EObject eObject, BaseAssessmentModel model) {
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(model);
        int featureValue = ((IntValue) eObject).getFeatureValue();
        myAccessor.setPropertyValue("index", featureValue);
    }
}
