package org.flickit.dslparser.model.assessmentkit;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class LevelModel extends BaseAssessmentModel {
    private Map<String, Integer> levelCompetence;

}
