package org.flickit.dslparser.model.profile;

import lombok.Data;

@Data
public class ImpactModel {
    private String attributeCode;
    private Integer level;
    private MetricModel metric;
}
