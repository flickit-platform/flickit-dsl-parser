package org.flickit.dslparser.service.xtext.extractor.feature;

import org.flickit.dslparser.model.profile.BaseAssessmentModel;
import org.flickit.dslparser.model.profile.SubjectModel;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.SubjectExtractor;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.SubjectFeature;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SubjectFeatureExtractor implements FeatureExtractor {

    @Autowired
    @Qualifier("subject")
    SubjectExtractor subjectExtractor;

    @Override
    public void extract(EObject eObject, BaseAssessmentModel model) {
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(model);
        SubjectModel subjectModel = subjectExtractor.extract(((SubjectFeature) eObject).getFeatureValue().get(0));
        myAccessor.setPropertyValue("subjectCode", subjectModel.getCode());
    }

}
