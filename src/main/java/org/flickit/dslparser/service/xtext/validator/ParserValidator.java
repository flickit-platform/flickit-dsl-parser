package org.flickit.dslparser.service.xtext.validator;

import lombok.RequiredArgsConstructor;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Attribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Subject;
import org.flickit.dsl.editor.v2.assessmentKitDsl.impl.RootImpl;
import org.flickit.dslparser.controller.exception.api.SyntaxError;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.AttributeExtractor;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.SubjectExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParserValidator {

    private final SubjectModelValidator subjectModelValidator;
    private final SubjectExtractor subjectExtractor;
    private final AttributeExtractor attributeExtractor;

    public List<SyntaxError> validate(RootImpl assessmentKit) {
        ModelValidator.Context context = createContext(assessmentKit);
        return subjectModelValidator.validate(context);
    }

    private ModelValidator.Context createContext(RootImpl assessmentKit) {
        XtextModel<Subject> subjectModels = subjectExtractor.extractModel(assessmentKit.getElements());
        XtextModel<Attribute> attributeModels = attributeExtractor.extractModel(assessmentKit.getElements());
        return new ModelValidator.Context(subjectModels, attributeModels);
    }
}
