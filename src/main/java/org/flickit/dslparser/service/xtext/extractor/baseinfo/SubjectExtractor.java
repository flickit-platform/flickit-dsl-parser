package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.flickit.dslparser.model.profile.SubjectModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.xtext.extractor.feature.FeatureExtractor;
import org.flickit.dslparser.service.xtext.extractor.feature.FeatureExtractorFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("subject")
public class SubjectExtractor implements BaseInfoExtractor<SubjectModel, Subject> {

    @Autowired
    FeatureExtractorFactory extractorFactory;

    @Override
    public SubjectModel extract(Subject xtextInfo) {
        EList<EObject> features = xtextInfo.getFeatures();
        SubjectModel subjectModel = new SubjectModel();
        for(EObject eObject: features) {
            FeatureExtractor featureExtractor = extractorFactory.getExtractor(eObject);
            featureExtractor.extract(eObject, subjectModel);
        }
        return subjectModel;
    }

    public List<SubjectModel> extractList(EList<BaseInfo> xtextElements) {
        XtextModel<Subject> xtextModel =  extractModel(xtextElements);
        List<Subject> xtextSubjects = xtextModel.getModels();
        List<SubjectModel> subjectModels = new ArrayList<>();
        for (int i = 0; i < xtextSubjects.size(); i++) {
            SubjectModel subjectModel = extract(xtextSubjects.get(i));
            if(subjectModel.getIndex() == null) {
                int index = i+1;
                subjectModel.setIndex(index);
            }
            subjectModels.add(subjectModel);
        }
        return subjectModels;
    }

    public XtextModel<Subject> extractModel(EList<BaseInfo> elements) {
        XtextModel<Subject> xtextModel = new XtextModel<>();
        List<Subject> models = new ArrayList<>();
        for(BaseInfo element : elements) {
            if(Subject.class.isAssignableFrom(element.getClass())) {
                Subject model = (Subject) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

}
