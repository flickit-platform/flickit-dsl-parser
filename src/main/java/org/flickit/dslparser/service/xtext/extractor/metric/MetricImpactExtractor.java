package org.flickit.dslparser.service.xtext.extractor.metric;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.profile.AffectsLevel;
import org.flickit.dsl.editor.profile.CustomOption;
import org.flickit.dsl.editor.profile.Metric;
import org.flickit.dslparser.model.profile.ImpactModel;
import org.flickit.dslparser.model.profile.LevelModel;
import org.flickit.dslparser.model.profile.MetricModel;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MetricImpactExtractor {

    public static final List<Double> DEFAULT_2_OPTION_VALUES = Arrays.asList(0d, 1d);
    public static final List<Double> DEFAULT_3_OPTION_VALUES = Arrays.asList(0d, 0.5d, 1d);
    public static final List<Double> DEFAULT_4_OPTION_VALUES = Arrays.asList(0d, 0.5d, 0.7d, 1.0d);
    public static final List<Double> DEFAULT_5_OPTION_VALUES = Arrays.asList(0d, 0.2d, 0.5d, 0.9d , 1d);

    public void setupMetricImpacts(MetricModel metricModel, Metric metric) {
        for(AffectsLevel affectLevel: metric.getAffects()) {
            ImpactModel impactModel = new ImpactModel();
            LevelModel levelModel = new LevelModel();
            levelModel.setTitle(affectLevel.getLevel().getTitle());
            impactModel.setLevel(levelModel);
            impactModel.setAttributeCode(affectLevel.getQualityAttribute().getDescription());
            extractMetricImpacts(affectLevel, impactModel, metricModel);
        }

        for(CustomOption customOption: metric.getCustomOptions()) {
            ImpactModel impactModel = new ImpactModel();
            LevelModel levelModel = new LevelModel();
            levelModel.setTitle(customOption.getOptionLevel().get(0).getTitle());
            impactModel.setLevel(levelModel);
            impactModel.setAttributeCode(customOption.getQualityAttribute().get(0).getDescription());
            extractCustomMetricImpact(customOption, impactModel, metricModel);
        }

    }

    private void extractCustomMetricImpact(CustomOption customOption, ImpactModel impactModel, MetricModel metricModel) {
        Map<Integer, Double> optionValueMap = new HashMap<>();
        EList<String> values = customOption.getValues();
        int j = 0;
        for(int i = customOption.getOptionFrom().get(0); i <= customOption.getOptionTo().get(0); i ++) {
            String value = values.get(j);
            j++;
            String formattedValue = value.charAt(0) + "." + value.charAt(2);
            optionValueMap.put(i, Double.valueOf(formattedValue));
        }
        impactModel.setOptionValues(optionValueMap);
        metricModel.addToImpacts(impactModel);
    }

    private void extractMetricImpacts(AffectsLevel affectLevel, ImpactModel impactModel, MetricModel metricModel) {
        Map<Integer, Double> optionValueMap = new HashMap<>();
        int optionNumber = metricModel.getAnswers().size();
        if(affectLevel.getValues() != null && !affectLevel.getValues().isEmpty()) {
            EList<String> values = affectLevel.getValues();
            for(int i = 0; i < optionNumber; i ++) {
                String value = values.get(i);
                String formattedValue = value.charAt(0) + "." + value.charAt(2);
                optionValueMap.put(i + 1, Double.valueOf(formattedValue));
            }
            impactModel.setOptionValues(optionValueMap);
        } else {
            List<Double> defaultImpact = getDefaultImpact(optionNumber);
            for(int i=0; i < defaultImpact.size(); i++) {
                optionValueMap.put(i + 1, defaultImpact.get(i));
            }
            impactModel.setOptionValues(optionValueMap);
        }
        metricModel.addToImpacts(impactModel);
    }

    private List<Double> getDefaultImpact(int optionNumber) {
        switch (optionNumber) {
            case 2:
                return DEFAULT_2_OPTION_VALUES;
            case 3:
                return DEFAULT_3_OPTION_VALUES;
            case 4:
                return DEFAULT_4_OPTION_VALUES;
            case 5:
                return DEFAULT_5_OPTION_VALUES;
            default:
                throw new IllegalArgumentException("Invalid option number.");
        }
    }
}
