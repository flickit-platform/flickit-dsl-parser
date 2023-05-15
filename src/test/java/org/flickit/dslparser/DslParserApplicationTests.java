package org.flickit.dslparser;

import org.apache.commons.io.FileUtils;
import org.flickit.dslparser.controller.AssessmentProfileResponse;
import org.flickit.dslparser.service.AssessmentProfileExtractor;
import org.flickit.dslparser.service.xtext.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class DslParserApplicationTests {

	@Autowired
	AssessmentProfileExtractor assessmentProfileExtractor;



	@Test
	void extractTest() {

		AssessmentProfileResponse resp = assessmentProfileExtractor.extract(readDslContent());
		System.out.println(resp);
	}

	private String readDslContent() {
		try{
			byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/dsl-sample/sample.profile"));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
