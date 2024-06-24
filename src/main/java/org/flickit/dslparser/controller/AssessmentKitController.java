package org.flickit.dslparser.controller;

import lombok.RequiredArgsConstructor;
import org.flickit.dslparser.service.xtext.AssessmentKitExtractor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AssessmentKitController {

    private final AssessmentKitExtractor extractor;

    @PostMapping(path= "/extract", consumes = "application/json", produces = "application/json")
    public AssessmentKitResponse extractAssessmentKit(@RequestBody AssessmentKitRequest request) {
        String dslContent = request.dslContent();
        return extractor.extract(dslContent);
    }
}
