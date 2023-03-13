package org.flickit.dslparser.controller;

import org.flickit.dslparser.service.AssessmentProfileExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssessmentProfileController {

    @Autowired
    AssessmentProfileExtractor extractor;

    @PostMapping(path= "/extract", consumes = "application/json", produces = "application/json")
    public AssessmentProfileResponse extractAssessmentProfile(@RequestBody AssessmentProfileRequest request) {
        String dslContent = request.getDslContent();
        AssessmentProfileResponse response = extractor.extract(dslContent);
        return response;
    }


}
