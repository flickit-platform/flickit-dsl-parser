package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.Questionnaire;
import org.flickit.dsl.editor.profile.Subject;
import org.flickit.dslparser.model.profile.SubjectModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("subject")
public class SubjectExtractor implements BaseInfoExtractor<SubjectModel, Subject> {

    @Autowired
    CodeGenerator codeGenerator;

    public List<SubjectModel> extractList(EList<BaseInfo> xtextElements) {
        XtextModel<Subject> xtextModel =  extractModel(xtextElements);
        List<Subject> xtextSubjects = xtextModel.getModels();
        List<SubjectModel> subjectModels = new ArrayList<>();
        for (int i = 0; i < xtextSubjects.size(); i++) {
            SubjectModel subjectModel = extract(xtextSubjects.get(i));
            setupIndex(i, subjectModel);
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
                model.setCode(codeGenerator.generate());
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

    @Override
    public SubjectModel extract(Subject subject) {
        SubjectModel subjectModel = new SubjectModel();
        subjectModel.setCode(subject.getCode());
        subjectModel.setTitle((subject.getTitle()));
        subjectModel.setDescription(subject.getDescription());
        subjectModel.setQuestionnaireCodes(subject.getQuestionnaires().stream().map(Questionnaire::getCode).collect(Collectors.toList()));
        return subjectModel;
    }

}
