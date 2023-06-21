package org.flickit.dslparser.model.assessmentkit;

import lombok.Data;

import java.util.Map;

@Data
public class ImpactModel {
    private String attributeCode;
    private LevelModel level;
    private QuestionModel question;

    private Map<Integer, Double> optionValues;

    private Integer weight;

}
