package org.flickit.dslparser.service.xtextv2.validator;

import lombok.RequiredArgsConstructor;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Attribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Subject;
import org.flickit.dsl.editor.v2.assessmentKitDsl.impl.RootImpl;
import org.flickit.dslparser.controller.exception.api.SyntaxError;
import org.flickit.dslparser.model.xtext.XtextV2Model;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.AttributeV2Extractor;
import org.flickit.dslparser.service.xtextv2.extractor.baseinfo.SubjectV2Extractor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParserValidator {

    private final SubjectModelValidator subjectModelValidator;
    private final SubjectV2Extractor subjectExtractor;
    private final AttributeV2Extractor attributeExtractor;

    public List<SyntaxError> validate(RootImpl assessmentKit) {
        ModelValidator.Context context = createContext(assessmentKit);
        return subjectModelValidator.validate(context);
    }

    private ModelValidator.Context createContext(RootImpl assessmentKit) {
        XtextV2Model<Subject> subjectModels = subjectExtractor.extractModel(assessmentKit.getElements());
        XtextV2Model<Attribute> attributeModels = attributeExtractor.extractModel(assessmentKit.getElements());
        return new ModelValidator.Context(subjectModels, attributeModels);
    }
}
