package org.flickit.dslparser.service.xtext.extractor.feature;

import org.flickit.dslparser.model.profile.BaseAssessmentModel;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.StringValue;
import org.springframework.stereotype.Component;

@Component
public class StringFeatureExtractor implements FeatureExtractor{

    @Override
    public void extract(EObject eObject, BaseAssessmentModel model) {
        StringValue stringValue = (StringValue) eObject;
        if(stringValue.getName().equals("code")) {
            model.setCode(stringValue.getFeatureValue());
        } else if(stringValue.getName().equals("title")) {
            model.setTitle(stringValue.getFeatureValue());
        } else if(stringValue.getName().equals("description")) {
            model.setDescription(stringValue.getFeatureValue());
        }
    }
}
