package org.flickit.dslparser.controller;

import org.flickit.dslparser.service.AssessmentKitV2Extractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssessmentKitController {

    @Autowired
    AssessmentKitV2Extractor v2Extractor;

    @PostMapping(path= "/extract", consumes = "application/json", produces = "application/json")
    public AssessmentKitResponse extractAssessmentKit(@RequestBody AssessmentKitRequest request) {
        String dslContent = request.getDslContent();
        return v2Extractor.extract(dslContent);
    }
}
