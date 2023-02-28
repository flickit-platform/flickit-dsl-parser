package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.flickit.dslparser.model.profile.AttributeModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.xtext.extractor.feature.FeatureExtractor;
import org.flickit.dslparser.service.xtext.extractor.feature.FeatureExtractorFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.QualityAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("attribute")
public class AttributeExtractor implements BaseInfoExtractor<AttributeModel, QualityAttribute> {

    @Autowired
    FeatureExtractorFactory extractorFactory;

    @Override
    public AttributeModel extract(QualityAttribute qualityAttribute) {
        EList<EObject> features = qualityAttribute.getFeatures();
        AttributeModel attributeModel = new AttributeModel();
        for(EObject eObject: features) {
            FeatureExtractor featureExtractor = extractorFactory.getExtractor(eObject);
            featureExtractor.extract(eObject, attributeModel);
        }
        return attributeModel;
    }

    @Override
    public List<AttributeModel> extractList(EList<BaseInfo> elements) {
        XtextModel<QualityAttribute> xtextModel = extractModel(elements);
        List<QualityAttribute> xtextAtts = xtextModel.getModels();
        List<AttributeModel> attributeModels = new ArrayList<>();
        for (int i = 0; i < xtextAtts.size(); i++) {
            AttributeModel attributeModel = extract(xtextAtts.get(i));
            setupIndex(i, attributeModel);
            attributeModels.add(attributeModel);
        }
        return attributeModels;
    }

    private void setupIndex(int i, AttributeModel attributeModel) {
        if(attributeModel.getIndex() == null) {
            attributeModel.setIndex(i + 1);
        }
    }

    @Override
    public XtextModel<QualityAttribute> extractModel(EList<BaseInfo> elements) {
        XtextModel<QualityAttribute> xtextModel = new XtextModel<>();
        List<QualityAttribute> models = new ArrayList<>();
        for(BaseInfo element : elements) {
            if(QualityAttribute.class.isAssignableFrom(element.getClass())) {
                QualityAttribute model = (QualityAttribute) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }


}
