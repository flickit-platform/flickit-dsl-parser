package org.flickit.dslparser.service.xtextv2.validator;

import org.flickit.dsl.editor.v2.assessmentKitDsl.Attribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Subject;
import org.flickit.dslparser.controller.exception.api.SyntaxError;
import org.flickit.dslparser.model.xtext.XtextV2Model;

import java.util.List;

public interface ModelValidator {

    List<SyntaxError> validate(Context context);

    record Context(XtextV2Model<Subject> subjectModels,
                   XtextV2Model<Attribute> attributeModels){
    }
}
