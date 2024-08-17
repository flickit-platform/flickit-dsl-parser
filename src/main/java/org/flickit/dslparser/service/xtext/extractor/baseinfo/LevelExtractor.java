package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Competence;
import org.flickit.dsl.editor.v2.assessmentKitDsl.CompetenceValue;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Level;
import org.flickit.dslparser.model.assessmentkit.LevelModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LevelExtractor implements BaseInfoExtractor<LevelModel, Level> {

    @Override
    public List<LevelModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Level> xtextModel = extractModel(elements);
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
        for (BaseInfo element : elements) {
            if (Level.class.isAssignableFrom(element.getClass())) {
                Level model = (Level) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

    @Override
    public LevelModel extract(Level level) {
        LevelModel levelModel = new LevelModel();
        levelModel.setCode(level.getName());
        levelModel.setTitle(level.getTitle());
        levelModel.setDescription(level.getDescription());
        levelModel.setValue(level.getValue());
        if (!level.getCompetence().isEmpty()) {
            Competence levelCompetence = level.getCompetence().get(0);
            EList<CompetenceValue> competenceValues = levelCompetence.getCompetenceValues();
            Map<String, Integer> levelCompetenceMap = new HashMap<>();
            for (CompetenceValue competenceValue : competenceValues) {
                levelCompetenceMap.put(competenceValue.getLevel().getName(), competenceValue.getValue());
            }
            levelModel.setLevelCompetence(levelCompetenceMap);
        }
        return levelModel;
    }
}
