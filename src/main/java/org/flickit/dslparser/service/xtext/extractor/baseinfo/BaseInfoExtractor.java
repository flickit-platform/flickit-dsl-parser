package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dslparser.model.assessmentkit.BaseAssessmentModel;
import org.flickit.dslparser.model.xtext.XtextModel;

import java.util.List;

public interface BaseInfoExtractor<T extends BaseAssessmentModel, X extends BaseInfo> {
    T extract(X xtextInfo);

    List<T> extractList(EList<BaseInfo> elements);

    XtextModel<X> extractModel(EList<BaseInfo> elements);

    default void setupIndex(int i, T model) {
        if (model.getIndex() == null || model.getIndex() == -1) {
            model.setIndex(i + 1);
        }
    }
}
