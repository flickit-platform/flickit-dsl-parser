package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.flickit.dslparser.model.assessmentkit.BaseAssessmentModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.assessmentKitDsl.BaseInfo;

import java.util.List;

public interface BaseInfoExtractor<T extends BaseAssessmentModel, X extends BaseInfo> {
    T extract(X XtextInfo);

    List<T> extractList(EList<BaseInfo> elements);

    XtextModel<X> extractModel(EList<BaseInfo> elements);

    public default void setupIndex(int i, T model) {
        if(model.getIndex() == null) {
            model.setIndex(i + 1);
        }
    }

}
