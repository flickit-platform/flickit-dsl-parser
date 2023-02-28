package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.flickit.dslparser.model.xtext.XtextModel;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.profile.BaseInfo;

import java.util.List;

public interface BaseInfoExtractor<T, X extends BaseInfo> {
    T extract(X XtextInfo);

    List<T> extractList(EList<BaseInfo> elements);

    XtextModel<X> extractModel(EList<BaseInfo> elements);

}
