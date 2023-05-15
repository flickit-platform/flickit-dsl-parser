package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.Level;
import org.flickit.dslparser.model.profile.LevelModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LevelExtractor implements BaseInfoExtractor<LevelModel, Level>{
    @Override
    public LevelModel extract(Level XtextInfo) {
        return null;
    }

    @Override
    public List<LevelModel> extractList(EList<BaseInfo> elements) {
        return null;
    }

    @Override
    public XtextModel<Level> extractModel(EList<BaseInfo> elements) {
        return null;
    }
}
