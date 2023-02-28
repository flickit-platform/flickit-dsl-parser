package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.flickit.dslparser.model.profile.MetricModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.xtext.extractor.feature.FeatureExtractor;
import org.flickit.dslparser.service.xtext.extractor.feature.FeatureExtractorFactory;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("metric")
@Slf4j
public class MetricExtractor implements BaseInfoExtractor<MetricModel, Metric> {

    @Autowired
    FeatureExtractorFactory extractorFactory;


    @Override
    public MetricModel extract(Metric xtextInfo) {
        EList<EObject> features = xtextInfo.getFeatures();
        MetricModel metricModel = new MetricModel();
        for(EObject eObject: features) {
            FeatureExtractor featureExtractor = extractorFactory.getExtractor(eObject);
            featureExtractor.extract(eObject, metricModel);
        }
        return metricModel;
    }

    @Override
    public List<MetricModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Metric> xtextModel =  extractModel(elements);
        List<Metric> xtextMetrics = xtextModel.getModels();
        List<MetricModel> metricModels = new ArrayList<>();
        for (int i = 0; i < xtextMetrics.size(); i++) {
            MetricModel metricModel = extract(xtextMetrics.get(i));
            if(metricModel.getIndex() == null) {
                int index = i+1;
                metricModel.setIndex(index);
            }
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
