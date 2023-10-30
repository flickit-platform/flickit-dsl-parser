package org.flickit.dslparser.service.xtextv2.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.v2.assessmentKitDsl.Attribute;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dslparser.model.assessmentkit.AttributeModel;
import org.flickit.dslparser.model.xtext.XtextV2Model;
import org.flickit.dslparser.service.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AttributeV2Extractor implements BaseInfoExtractor<AttributeModel, Attribute> {

    @Autowired
    CodeGenerator codeGenerator;

    @Override
    public List<AttributeModel> extractList(EList<BaseInfo> elements) {
        XtextV2Model<Attribute> xtextV2Model = extractModel(elements);
        List<Attribute> xtextAtts = xtextV2Model.getModels();
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
    public XtextV2Model<Attribute> extractModel(EList<BaseInfo> elements) {
        XtextV2Model<Attribute> xtextV2Model = new XtextV2Model<>();
        List<Attribute> models = new ArrayList<>();
        for (BaseInfo element : elements) {
            if (Attribute.class.isAssignableFrom(element.getClass())) {
                Attribute model = (Attribute) element;
                model.setName(codeGenerator.generate());
                models.add(model);
            }
        }
        xtextV2Model.setModels(models);
        return xtextV2Model;
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
