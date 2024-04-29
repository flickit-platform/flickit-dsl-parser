package org.flickit.dslparser.service.xtextv2.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Competence;
import org.flickit.dsl.editor.v2.assessmentKitDsl.CompetenceValue;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Level;
import org.flickit.dslparser.model.assessmentkit.LevelModel;
import org.flickit.dslparser.model.xtext.XtextV2Model;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LevelV2Extractor implements BaseInfoExtractor<LevelModel, Level> {

    @Override
    public List<LevelModel> extractList(EList<BaseInfo> elements) {
        XtextV2Model<Level> xtextV2Model = extractModel(elements);
        List<Level> xtextLevels = xtextV2Model.getModels();
        List<LevelModel> levelModels = new ArrayList<>();
        for (int i = 0; i < xtextLevels.size(); i++) {
            LevelModel levelModel = extract(xtextLevels.get(i));
            setupIndex(i, levelModel);
            levelModels.add(levelModel);
        }
        return levelModels;
    }

    @Override
    public XtextV2Model<Level> extractModel(EList<BaseInfo> elements) {
        XtextV2Model<Level> xtextV2Model = new XtextV2Model<>();
        List<Level> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Level.class.isAssignableFrom(element.getClass())) {
                Level model = (Level) element;
                models.add(model);
            }
        }
        xtextV2Model.setModels(models);
        return xtextV2Model;
    }

    @Override
    public LevelModel extract(Level level) {
        LevelModel levelModel = new LevelModel();
        levelModel.setCode(level.getName());
        levelModel.setTitle((level.getTitle()));
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
