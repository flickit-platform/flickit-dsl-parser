package org.flickit.dslparser.service.xtext.validator;

import org.flickit.dsl.editor.v2.assessmentKitDsl.Attribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Subject;
import org.flickit.dslparser.controller.exception.api.SyntaxError;
import org.flickit.dslparser.model.xtext.XtextModel;

import java.util.List;

public interface ModelValidator {

    List<SyntaxError> validate(Context context);

    record Context(XtextModel<Subject> subjectModels,
                   XtextModel<Attribute> attributeModels){
    }
}
