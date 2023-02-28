package org.flickit.dslparser.service.xtext.extractor.feature;

import org.flickit.dslparser.model.profile.BaseAssessmentModel;
import org.flickit.dslparser.model.profile.ImpactModel;
import org.flickit.dslparser.model.profile.MetricModel;
import org.flickit.dslparser.service.xtext.extractor.baseinfo.AttributeExtractor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.flickit.dsl.editor.profile.ImpactLevel1;
import org.flickit.dsl.editor.profile.ImpactLevel2;
import org.flickit.dsl.editor.profile.ImpactLevel3;
import org.flickit.dsl.editor.profile.QualityAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class ImpactLevelExtractor implements FeatureExtractor {

    @Autowired
    AttributeExtractor attributeExtractor;

    @Override
    public void extract(EObject eObject, BaseAssessmentModel model) {
        EList<QualityAttribute> attributes = getAttributeEList(eObject);
        int level = getLevel(eObject);
        for(QualityAttribute att: attributes) {
            ImpactModel impactModel = new ImpactModel();
            impactModel.setAttributeCode(attributeExtractor.extract(att).getCode());
            impactModel.setLevel(level);
            addImpactToMetricModel(model, impactModel);
        }
    }

    private static EList<QualityAttribute> getAttributeEList(EObject eObject) {
        EList<QualityAttribute> attributes = null;
        if(eObject instanceof ImpactLevel1) {
            attributes = ((ImpactLevel1) eObject).getFeatureValue();
        } else if(eObject instanceof ImpactLevel2) {
            attributes = ((ImpactLevel2) eObject).getFeatureValue();
        } else if(eObject instanceof ImpactLevel3) {
            attributes = ((ImpactLevel3) eObject).getFeatureValue();
        }
        return attributes;
    }

    private static Integer getLevel(EObject eObject) {
        if(eObject instanceof ImpactLevel1) {
            return 1;
        } else if(eObject instanceof ImpactLevel2) {
            return 2;
        } else if(eObject instanceof ImpactLevel3) {
            return 3;
        }
        return 0;
    }

    private void addImpactToMetricModel(BaseAssessmentModel model, ImpactModel impactModel) {
        try {
            Method addToImpactsMethod = MetricModel.class.getMethod("addToImpacts", ImpactModel.class);
            addToImpactsMethod.invoke(model, impactModel);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
