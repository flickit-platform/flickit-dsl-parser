package org.flickit.dslparser.service.xtext;

import org.flickit.dslparser.model.xtext.XtextModel;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelExtractor {

    @SuppressWarnings("unchecked")
    public <T extends BaseInfo> XtextModel<T> extractModels(EList<BaseInfo> assessmentProfileElements, Class<T> clazz) {
        XtextModel<T> xtextModel = new XtextModel<>();
        List<T> models = new ArrayList<>();
        for(BaseInfo element : assessmentProfileElements) {
            if (element.getClass().isAssignableFrom(clazz)) {
                T model = (T) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

}
