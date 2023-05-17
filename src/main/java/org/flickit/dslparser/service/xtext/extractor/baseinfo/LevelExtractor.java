package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.profile.*;
import org.flickit.dslparser.model.profile.LevelModel;
import org.flickit.dslparser.model.profile.SubjectModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LevelExtractor implements BaseInfoExtractor<LevelModel, Level>{
    @Override
    public LevelModel extract(Level level) {
        LevelModel levelModel = new LevelModel();
        levelModel.setTitle((level.getTitle()));
        if (!level.getCompetence().isEmpty()) {
            Competence levelCompetence = level.getCompetence().get(0);
            EList<CompetenceValue> competenceValues = levelCompetence.getCompetenceValues();
            Map<String, Integer> levelCompetenceMap = new HashMap<>();
            for (CompetenceValue competenceValue: competenceValues) {
                levelCompetenceMap.put(competenceValue.getLevel().getTitle(), competenceValue.getValue());
            }
            levelModel.setLevelCompetence(levelCompetenceMap);
        }


        return levelModel;
    }

    @Override
    public List<LevelModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Level> xtextModel =  extractModel(elements);
        List<Level> xtextLevels = xtextModel.getModels();
        List<LevelModel> levelModels = new ArrayList<>();
        for (int i = 0; i < xtextLevels.size(); i++) {
            LevelModel levelModel = extract(xtextLevels.get(i));
            setupIndex(i, levelModel);
            levelModels.add(levelModel);
        }
        return levelModels;
    }

    @Override
    public XtextModel<Level> extractModel(EList<BaseInfo> elements) {
        XtextModel<Level> xtextModel = new XtextModel<>();
        List<Level> models = new ArrayList<>();
        for(BaseInfo element : elements) {
            if(Level.class.isAssignableFrom(element.getClass())) {
                Level model = (Level) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }
}
