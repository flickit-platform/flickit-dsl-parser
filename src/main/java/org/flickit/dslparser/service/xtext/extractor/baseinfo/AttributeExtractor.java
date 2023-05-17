package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.QualityAttribute;
import org.flickit.dslparser.model.profile.AttributeModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.flickit.dslparser.service.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("attribute")
public class AttributeExtractor implements BaseInfoExtractor<AttributeModel, QualityAttribute> {

    @Autowired
    CodeGenerator codeGenerator;

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

    @Override
    public XtextModel<QualityAttribute> extractModel(EList<BaseInfo> elements) {
        XtextModel<QualityAttribute> xtextModel = new XtextModel<>();
        List<QualityAttribute> models = new ArrayList<>();
        for(BaseInfo element : elements) {
            if(QualityAttribute.class.isAssignableFrom(element.getClass())) {
                QualityAttribute model = (QualityAttribute) element;
                model.setCode(codeGenerator.generate());
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }

    @Override
    public AttributeModel extract(QualityAttribute qualityAttribute) {
        AttributeModel attributeModel = new AttributeModel();
        attributeModel.setCode(qualityAttribute.getCode());
        attributeModel.setTitle(qualityAttribute.getTitle());
        attributeModel.setDescription(qualityAttribute.getDescription());
        attributeModel.setSubjectCode(qualityAttribute.getSubject().getCode());
        attributeModel.setWeight(qualityAttribute.getWeight() != 0 ? qualityAttribute.getWeight():1);
        return attributeModel;
    }
}
