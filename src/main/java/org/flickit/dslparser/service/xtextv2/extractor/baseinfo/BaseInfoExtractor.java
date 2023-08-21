package org.flickit.dslparser.service.xtextv2.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dslparser.model.assessmentkit.BaseAssessmentModel;
import org.flickit.dslparser.model.xtext.XtextV2Model;

import java.util.List;

public interface BaseInfoExtractor<T extends BaseAssessmentModel, X extends BaseInfo> {
    T extract(X XtextInfo);

    List<T> extractList(EList<BaseInfo> elements);

    XtextV2Model<X> extractModel(EList<BaseInfo> elements);

    public default void setupIndex(int i, T model) {
        if (model.getIndex() == null) {
            model.setIndex(i + 1);
        }
    }
}
