package org.flickit.dslparser.service.xtext.extractor.baseinfo;

import org.flickit.dslparser.model.profile.ProfileModel;
import org.flickit.dslparser.model.xtext.XtextModel;
import org.eclipse.emf.common.util.EList;
import org.flickit.dsl.editor.profile.BaseInfo;
import org.flickit.dsl.editor.profile.Profile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("profile")
public class ProfileExtractor implements BaseInfoExtractor<ProfileModel, Profile> {

    @Override
    public ProfileModel extract(Profile xtextInfo) {
        return new ProfileModel();
    }

    @Override
    public List<ProfileModel> extractList(EList<BaseInfo> elements) {
        XtextModel<Profile> xtextModel =  extractModel(elements);
        List<Profile> xtextProfiles = xtextModel.getModels();
        List<ProfileModel> profileModels = new ArrayList<>();
        for (Profile xtextProfile : xtextProfiles) {
            profileModels.add(extract(xtextProfile));
        }
        return profileModels;
    }

    @Override
    public XtextModel<Profile> extractModel(EList<BaseInfo> elements) {
        XtextModel<Profile> xtextModel = new XtextModel<>();
        List<Profile> models = new ArrayList<>();
        for(BaseInfo element : elements) {
            if(Profile.class.isAssignableFrom(element.getClass())) {
                Profile model = (Profile) element;
                models.add(model);
            }
        }
        xtextModel.setModels(models);
        return xtextModel;
    }
}
