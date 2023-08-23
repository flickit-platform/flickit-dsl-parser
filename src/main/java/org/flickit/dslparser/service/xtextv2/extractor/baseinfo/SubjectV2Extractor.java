package org.flickit.dslparser.service.xtextv2.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Subject;
import org.flickit.dslparser.model.assessmentkit.SubjectModel;
import org.flickit.dslparser.model.xtext.XtextV2Model;
import org.flickit.dslparser.service.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectV2Extractor implements BaseInfoExtractor<SubjectModel, Subject> {

    @Autowired
    CodeGenerator codeGenerator;

    private static void setupWeight(SubjectModel subjectModel) {
        subjectModel.setWeight(subjectModel.getWeight() == null ? 1 : subjectModel.getWeight());
    }

    public List<SubjectModel> extractList(EList<BaseInfo> xtextElements) {
        XtextV2Model<Subject> xtextV2Model = extractModel(xtextElements);
        List<Subject> xtextSubjects = xtextV2Model.getModels();
        List<SubjectModel> subjectModels = new ArrayList<>();
        for (int i = 0; i < xtextSubjects.size(); i++) {
            SubjectModel subjectModel = extract(xtextSubjects.get(i));
            setupIndex(i, subjectModel);
            setupWeight(subjectModel);
            subjectModels.add(subjectModel);
        }
        return subjectModels;
    }

    public XtextV2Model<Subject> extractModel(EList<BaseInfo> elements) {
        XtextV2Model<Subject> xtextV2Model = new XtextV2Model<>();
        List<Subject> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Subject.class.isAssignableFrom(element.getClass())) {
                Subject model = (Subject) element;
                model.setName(codeGenerator.generate());
                models.add(model);
            }
        }
        xtextV2Model.setModels(models);
        return xtextV2Model;
    }

    @Override
    public SubjectModel extract(Subject subject) {
        SubjectModel subjectModel = new SubjectModel();
        subjectModel.setCode(subject.getName());
        subjectModel.setTitle((subject.getTitle()));
        subjectModel.setDescription(subject.getDescription());
        subjectModel.setIndex(subject.getIndex());
        subjectModel.setWeight(subject.getWeight());
        return subjectModel;
    }
}
