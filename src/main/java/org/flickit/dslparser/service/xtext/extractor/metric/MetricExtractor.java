package org.flickit.dslparser.service.xtext.extractor.metric;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.profile.AffectsLevel;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.CustomOption;
import org.flickit.dsl.editor.profile.Metric;
import org.flickit.dslparser.model.profile.AnswerModel;

import org.flickit.dslparser.model.profile.ImpactModel;
import org.flickit.dslparser.model.profile.Level;
import org.flickit.dslparser.model.profile.MetricModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.BaseInfoExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Qualifier("metric")
@Slf4j
public class MetricExtractor implements BaseInfoExtractor<MetricModel, Metric> {

    @Autowired
    MetricOptionExtractor metricOptionExtractor;

    @Autowired
    MetricImpactExtractor metricImpactExtractor;

    @Override
    public MetricModel extract(Metric metric) {
        MetricModel metricModel = new MetricModel();
        metricModel.setQuestion(metric.getQuestion());
        metricModel.setQuestionnaireCode(metric.getQuestionnaire().getCode());
        metricOptionExtractor.setupMetricOptions(metricModel, metric.getOptions());
        metricImpactExtractor.setupMetricImpacts(metricModel, metric);
        return metricModel;
    }

    @Override
    public List<MetricModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Metric> xtextModel =  extractModel(elements);
        List<Metric> xtextMetrics = xtextModel.getModels();
        List<MetricModel> metricModels = new ArrayList<>();
        for (int i = 0; i < xtextMetrics.size(); i++) {
            MetricModel metricModel = extract(xtextMetrics.get(i));
            setupIndex(i, metricModel);
            metricModels.add(metricModel);
        }
        return metricModels;
    }

    @Override
    public XtextModel<Metric> extractModel(EList<BaseInfo> elements) {
        XtextModel<Metric> xtextModel = new XtextModel<>();
        List<Metric> models = new ArrayList<>();
        for(BaseInfo element : elements) {
            if(Metric.class.isAssignableFrom(element.getClass())) {
                Metric model = (Metric) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

}
