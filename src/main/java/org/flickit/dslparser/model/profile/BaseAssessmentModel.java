package org.flickit.dslparser.model.profile;

import lombok.Data;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.impl.StringValueImpl;

@Data
public class BaseAssessmentModel {
    private String code;
    private String title;
    private String description;

    public void extractBaseInfoModel(EObject eObject) {
        StringValueImpl stringProp = (StringValueImpl) eObject;
        if(stringProp.getName().equals("code")) {
            this.code = stringProp.getFeatureValue();
        } else if(stringProp.getName().equals("title")) {
            this.title = stringProp.getFeatureValue();
        } else if(stringProp.getName().equals("description")) {
            this.description = stringProp.getFeatureValue();
        }
    }
}
