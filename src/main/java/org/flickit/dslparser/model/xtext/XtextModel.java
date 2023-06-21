package org.flickit.dslparser.model.xtext;

import lombok.Getter;
import lombok.Setter;
import org.flickit.dsl.editor.assessmentKitDsl.BaseInfo;

import java.util.List;

@Getter
@Setter
public class XtextModel<T extends BaseInfo> {
    private List<T> models;
}
