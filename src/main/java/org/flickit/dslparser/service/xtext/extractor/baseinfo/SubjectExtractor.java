package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Subject;
import org.flickit.dslparser.model.assessmentkit.SubjectModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectExtractor implements BaseInfoExtractor<SubjectModel, Subject> {

    private static void setupWeight(SubjectModel subjectModel) {
        subjectModel.setWeight(subjectModel.getWeight() == null ? 1 : subjectModel.getWeight());
    }

    public List<SubjectModel> extractList(EList<BaseInfo> xtextElements) {
        XtextModel<Subject> xtextModel = extractModel(xtextElements);
        List<Subject> xtextSubjects = xtextModel.getModels();
        List<SubjectModel> subjectModels = new ArrayList<>();
        for (int i = 0; i < xtextSubjects.size(); i++) {
            SubjectModel subjectModel = extract(xtextSubjects.get(i));
            setupIndex(i, subjectModel);
            setupWeight(subjectModel);
            subjectModels.add(subjectModel);
        }
        return subjectModels;
    }

    public XtextModel<Subject> extractModel(EList<BaseInfo> elements) {
        XtextModel<Subject> xtextModel = new XtextModel<>();
        List<Subject> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Subject.class.isAssignableFrom(element.getClass())) {
                Subject model = (Subject) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

    @Override
    public SubjectModel extract(Subject subject) {
        SubjectModel subjectModel = new SubjectModel();
        subjectModel.setCode(subject.getName());
        subjectModel.setTitle((subject.getTitle()));
        subjectModel.setDescription(subject.getDescription());
        subjectModel.setIndex(subject.getIndex() == 0 ? -1 : subject.getIndex());
        subjectModel.setWeight(subject.getWeight());
        return subjectModel;
    }
}
