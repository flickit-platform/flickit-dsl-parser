package org.flickit.dslparser;

import org.flickit.dslparser.controller.AssessmentKitResponse;
import org.flickit.dslparser.model.assessmentkit.*;
import org.flickit.dslparser.service.AssessmentKitExtractor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
class DslParserApplicationTests {

	@Autowired
	AssessmentKitExtractor assessmentKitExtractor;



	@Test
	void extractTest() {
		AssessmentKitResponse resp = assessmentKitExtractor.extract(readDslContent());
		List<AttributeModel> atts = resp.getAttributeModels();
		List<SubjectModel> subjects = resp.getSubjectModels();
		List<QuestionnaireModel> questionnaires = resp.getQuestionnaireModels();
		List<QuestionModel> questions = resp.getQuestionModels();
		List<LevelModel> levelModels = resp.getLevelModels();

		// attribute assertion
		assertEquals( 6, atts.size());
		assertEquals("Software-Reliability", atts.get(0).getTitle());
		assertEquals("jkjkjkj", atts.get(0).getDescription());
		assertEquals(1, atts.get(0).getIndex());
		assertEquals(1, atts.get(0).getWeight());
		assertEquals(subjects.get(0).getCode(), atts.get(0).getSubjectCode());
		assertNotNull(atts.get(0).getCode());

		assertEquals("Team-Performance Stability", atts.get(5).getTitle());
		assertEquals("sdfgbfthb", atts.get(5).getDescription());
		assertEquals(6, atts.get(5).getIndex());
		assertEquals(3, atts.get(5).getWeight());
		assertEquals(subjects.get(1).getCode(), atts.get(5).getSubjectCode());
		assertNotNull(atts.get(5).getCode());

		// subject assertion
		assertEquals( 2, subjects.size());
		assertEquals("Software", subjects.get(0).getTitle());
		assertEquals("gfgfgfgf", subjects.get(0).getDescription());
		assertEquals(1, subjects.get(0).getIndex());
		assertNotNull(subjects.get(0).getCode());

		assertEquals("Team", subjects.get(1).getTitle());
		assertEquals("ghghghghg", subjects.get(1).getDescription());
		assertEquals(2, subjects.get(1).getIndex());
		assertNotNull(subjects.get(1).getCode());

		// questionnaire assertion
		assertEquals( 13, questionnaires.size());
		assertEquals("Clean Architecture", questionnaires.get(0).getTitle());
		assertEquals("tttttttt", questionnaires.get(0).getDescription());
		assertEquals(1, questionnaires.get(0).getIndex());
		assertNotNull(questionnaires.get(0).getCode());

		assertEquals("Quality Consequences", questionnaires.get(12).getTitle());
		assertEquals("zzzzzzzzzz", questionnaires.get(12).getDescription());
		assertEquals(13, questionnaires.get(12).getIndex());
		assertNotNull(questionnaires.get(12).getCode());

		// question assertion
		assertEquals("Is multilingualism and the ease of adding new languages supported?", questions.get(0).getTitle());
		assertEquals(1, questions.get(0).getIndex());
		assertEquals(questionnaires.get(7).getCode(), questions.get(0).getQuestionnaireCode());

		assertEquals(2, questions.get(0).getAnswers().size());
		assertEquals("No", questions.get(0).getAnswers().get(0).getCaption());
		assertEquals(1, questions.get(0).getAnswers().get(0).getIndex());
		assertEquals("Yes", questions.get(0).getAnswers().get(1).getCaption());
		assertEquals(2, questions.get(0).getAnswers().get(1).getIndex());

		assertEquals(2, questions.get(0).getQuestionImpacts().size());
		assertEquals(2, questions.get(0).getQuestionImpacts().get(0).getWeight());
		assertEquals("Weak", questions.get(0).getQuestionImpacts().get(0).getLevel().getTitle());
		assertEquals(2, questions.get(0).getQuestionImpacts().get(0).getOptionValues().size());
		assertTrue(questions.get(0).getQuestionImpacts().get(0).getOptionValues().containsKey(1));
		assertTrue(questions.get(0).getQuestionImpacts().get(0).getOptionValues().containsKey(2));
		assertEquals(atts.get(3).getCode(), questions.get(0).getQuestionImpacts().get(0).getAttributeCode());




		assertEquals("Are the APIs documented and tested, and are proper tools and standards employed to accomplish this?", questions.get(3).getTitle());
		assertEquals(2, questions.get(3).getIndex());
		assertEquals(questionnaires.get(8).getCode(), questions.get(3).getQuestionnaireCode());

		assertEquals(4, questions.get(3).getAnswers().size());
		assertEquals("Poor", questions.get(3).getAnswers().get(0).getCaption());
		assertEquals(1, questions.get(3).getAnswers().get(0).getIndex());
		assertEquals("Weak", questions.get(3).getAnswers().get(1).getCaption());
		assertEquals(2, questions.get(3).getAnswers().get(1).getIndex());

		assertEquals(3, questions.get(3).getQuestionImpacts().size());

		assertEquals(1, questions.get(3).getQuestionImpacts().get(0).getWeight());
		assertEquals("Weak", questions.get(3).getQuestionImpacts().get(0).getLevel().getTitle());
		assertEquals(4, questions.get(3).getQuestionImpacts().get(0).getOptionValues().size());
		assertTrue(questions.get(3).getQuestionImpacts().get(0).getOptionValues().containsKey(1));
		assertEquals(0.0, questions.get(3).getQuestionImpacts().get(0).getOptionValues().get(1));
		assertTrue(questions.get(3).getQuestionImpacts().get(0).getOptionValues().containsKey(2));
		assertEquals(0.0, questions.get(3).getQuestionImpacts().get(0).getOptionValues().get(2));
		assertTrue(questions.get(3).getQuestionImpacts().get(0).getOptionValues().containsKey(3));
		assertEquals(0.5, questions.get(3).getQuestionImpacts().get(0).getOptionValues().get(3));
		assertTrue(questions.get(3).getQuestionImpacts().get(0).getOptionValues().containsKey(3));
		assertEquals(1.0, questions.get(3).getQuestionImpacts().get(0).getOptionValues().get(4));
		assertEquals(atts.get(0).getCode(), questions.get(3).getQuestionImpacts().get(0).getAttributeCode());

		assertEquals(1, questions.get(3).getQuestionImpacts().get(1).getWeight());
		assertEquals("Elementary", questions.get(3).getQuestionImpacts().get(1).getLevel().getTitle());
		assertEquals(3, questions.get(3).getQuestionImpacts().get(1).getOptionValues().size());
		assertTrue(questions.get(3).getQuestionImpacts().get(1).getOptionValues().containsKey(1));
		assertEquals(0.0, questions.get(3).getQuestionImpacts().get(1).getOptionValues().get(1));
		assertTrue(questions.get(3).getQuestionImpacts().get(1).getOptionValues().containsKey(2));
		assertEquals(0.5, questions.get(3).getQuestionImpacts().get(1).getOptionValues().get(2));
		assertTrue(questions.get(3).getQuestionImpacts().get(1).getOptionValues().containsKey(3));
		assertEquals(1.0, questions.get(3).getQuestionImpacts().get(1).getOptionValues().get(3));
		assertEquals(atts.get(2).getCode(), questions.get(3).getQuestionImpacts().get(1).getAttributeCode());

		// level assertion
		assertEquals(5, levelModels.size());
		assertEquals("Elementary", levelModels.get(0).getTitle());
		assertNull(levelModels.get(0).getLevelCompetence());

		assertEquals("Weak", levelModels.get(1).getTitle());
		assertEquals(60, levelModels.get(1).getLevelCompetence().get(levelModels.get(1).getTitle()));

		assertEquals("Moderate", levelModels.get(2).getTitle());
		assertEquals(75, levelModels.get(2).getLevelCompetence().get(levelModels.get(1).getTitle()));
		assertEquals(60, levelModels.get(2).getLevelCompetence().get(levelModels.get(2).getTitle()));

		assertEquals("Good", levelModels.get(3).getTitle());
		assertEquals(85, levelModels.get(3).getLevelCompetence().get(levelModels.get(1).getTitle()));
		assertEquals(75, levelModels.get(3).getLevelCompetence().get(levelModels.get(2).getTitle()));
		assertEquals(60, levelModels.get(3).getLevelCompetence().get(levelModels.get(3).getTitle()));

		assertEquals("Great", levelModels.get(4).getTitle());
		assertEquals(95, levelModels.get(4).getLevelCompetence().get(levelModels.get(1).getTitle()));
		assertEquals(85, levelModels.get(4).getLevelCompetence().get(levelModels.get(2).getTitle()));
		assertEquals(75, levelModels.get(4).getLevelCompetence().get(levelModels.get(3).getTitle()));
		assertEquals(60, levelModels.get(4).getLevelCompetence().get(levelModels.get(4).getTitle()));

	}

	private String readDslContent() {
		try{
			byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/dsl-sample/sample.ak"));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
