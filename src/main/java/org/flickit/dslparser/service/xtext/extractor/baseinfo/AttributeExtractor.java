package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Attribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dslparser.model.assessmentkit.AttributeModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AttributeExtractor implements BaseInfoExtractor<AttributeModel, Attribute> {

    @Override
    public List<AttributeModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Attribute> xtextModel = extractModel(elements);
        List<Attribute> xtextAtts = xtextModel.getModels();
        List<AttributeModel> attributeModels = new ArrayList<>();
        for (int i = 0; i < xtextAtts.size(); i++) {
            AttributeModel attributeModel = extract(xtextAtts.get(i));
            setupIndex(i, attributeModel);
            attributeModels.add(attributeModel);
        }
        setupAttributeIndex(attributeModels);
        return attributeModels;
    }

    @Override
    public XtextModel<Attribute> extractModel(EList<BaseInfo> elements) {
        XtextModel<Attribute> xtextModel = new XtextModel<>();
        List<Attribute> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Attribute.class.isAssignableFrom(element.getClass())) {
                Attribute model = (Attribute) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

    @Override
    public AttributeModel extract(Attribute qualityAttribute) {
        AttributeModel attributeModel = new AttributeModel();
        attributeModel.setCode(qualityAttribute.getName());
        attributeModel.setTitle(qualityAttribute.getTitle());
        attributeModel.setDescription(qualityAttribute.getDescription());
        attributeModel.setIndex(qualityAttribute.getIndex() == 0 ? -1 : qualityAttribute.getIndex());
        attributeModel.setSubjectCode(qualityAttribute.getSubject().getName());
        attributeModel.setWeight(qualityAttribute.getWeight() != 0 ? qualityAttribute.getWeight() : 1);
        return attributeModel;
    }

    private static void setupAttributeIndex(List<AttributeModel> attributeModels) {
        Map<String, List<AttributeModel>> attributeBySubjectMap = attributeModels.stream().collect(Collectors.groupingBy(AttributeModel::getSubjectCode));
        for (List<AttributeModel> models : attributeBySubjectMap.values()) {
            int index = 1;
            for (AttributeModel model : models) {
                model.setIndex(index);
                index++;
            }
        }
    }
}
