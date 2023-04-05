package org.flickit.dslparser.model.profile;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ImpactModel {
    private String attributeCode;
    private Integer level;
    private MetricModel metric;

    private Map<Integer, Double> optionValues;

}
