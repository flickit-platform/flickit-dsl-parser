package org.flickit.dslparser;

import com.google.common.collect.Maps;
import org.flickit.dslparser.controller.AssessmentKitResponse;
import org.flickit.dslparser.model.assessmentkit.*;
import org.flickit.dslparser.service.AssessmentKitV2Extractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class DslParserV2ApplicationTests {

	private static final int SUBJECT_TEAM_INDEX = 0;
	private static final int QUESTIONNAIRE_DEV_OPS_INDEX = 0;
	private static final int ATTRIBUTE_TEAM_REFLECTION_INDEX = 0;
	private static final int LEVEL_ELEMENTARY_INDEX = 0;
	private static final int LEVEL_WEAK_INDEX = 1;
	private static final int LEVEL_MODERATE_INDEX = 2;

	@Autowired
	private AssessmentKitV2Extractor assessmentKitExtractor;

	private AssessmentKitResponse resp;

	@BeforeEach
	void extractKit() {
		if (resp == null) resp = assessmentKitExtractor.extract(readDslContent());
	}

	@Test
	void extractLevelTest() {
		List<LevelModel> levelModels = resp.getLevelModels();
		Map<String, Integer> expectedLevelCompetence = new HashMap<>();
		expectedLevelCompetence.put("Weak", 75);
		expectedLevelCompetence.put("Moderate", 60);

		assertEquals(3, levelModels.size());

		LevelModel levelModel = levelModels.get(LEVEL_MODERATE_INDEX);
		assertEquals("Moderate", levelModel.getCode());
		assertEquals("Moderate", levelModel.getTitle());
		assertEquals(3, levelModel.getIndex());
		assertEquals(2, levelModel.getValue());
		assertTrue(Maps.difference(expectedLevelCompetence, levelModel.getLevelCompetence()).areEqual());
	}

	@Test
	void extractSubjectTest() {
		List<SubjectModel> subjectModels = resp.getSubjectModels();

		assertEquals(2, subjectModels.size());

		SubjectModel subjectModel = subjectModels.get(SUBJECT_TEAM_INDEX);
		assertEquals("Team", subjectModel.getTitle());
		assertEquals("This is a test Description for Team Subject", subjectModel.getDescription());
		assertEquals(1, subjectModel.getIndex());
		assertEquals(1, subjectModel.getWeight());
	}

	@Test
	void extractQuestionnaireTest() {
		List<QuestionnaireModel> questionnaireModels = resp.getQuestionnaireModels();

		assertEquals(2, questionnaireModels.size());

		QuestionnaireModel questionnaireModel = questionnaireModels.get(QUESTIONNAIRE_DEV_OPS_INDEX);
		assertEquals("DEV OPS", questionnaireModel.getTitle());
		assertEquals("This is a test Description for DEV OPS category", questionnaireModel.getDescription());
		assertEquals(1, questionnaireModel.getIndex());
	}

	@Test
	void extractAttributeTest() {
		List<AttributeModel> attributeModels = resp.getAttributeModels();

		assertEquals(2, attributeModels.size());

		AttributeModel attributeModel = attributeModels.get(ATTRIBUTE_TEAM_REFLECTION_INDEX);
		assertEquals("Team Reflection", attributeModel.getTitle());
		assertEquals("This is a test Description for Team Reflection", attributeModel.getDescription());
		assertEquals(1, attributeModel.getIndex());
		assertEquals(resp.getSubjectModels().get(SUBJECT_TEAM_INDEX).getCode(), attributeModel.getSubjectCode());
		assertEquals(3, attributeModel.getWeight());

	}

	@Test
	void extractQuestionTest() {
		List<QuestionModel> questionModels = resp.getQuestionModels();
		List<String> expectedAnswers = Arrays.asList("Cap1", "Cap2", "Cap3");
		Map<Integer, Double> expectedOptionValues = createOptionValueWith(0, 1, 1);

		assertEquals(3, questionModels.size());

		QuestionModel questionModel = questionModels.get(1);
		assertEquals("q2", questionModel.getCode());
		assertEquals(resp.getQuestionnaireModels().get(QUESTIONNAIRE_DEV_OPS_INDEX).getCode(), questionModel.getQuestionnaireCode());
		assertEquals("metric question test m2?", questionModel.getTitle());
		assertEquals("This metric has impact on 3 attributes.", questionModel.getDescription());
		List<String> actualAnswers = questionModel.getAnswers().stream().map(AnswerModel::getCaption).collect(Collectors.toList());
		assertEquals(expectedAnswers, actualAnswers);
		assertTrue(questionModel.isMayNotBeApplicable());

		ImpactModel impactModel = questionModel.getQuestionImpacts().get(0);

		assertEquals(resp.getAttributeModels().get(ATTRIBUTE_TEAM_REFLECTION_INDEX).getCode(), impactModel.getAttributeCode());
		assertEquals(resp.getLevelModels().get(LEVEL_MODERATE_INDEX).getTitle(), impactModel.getLevel().getTitle());
		assertTrue(Maps.difference(expectedOptionValues, impactModel.getOptionValues()).areEqual());
		assertEquals(2, impactModel.getWeight());
	}

	@Test
	void extractQuestionWithMultipleImpactTest() {
		List<QuestionModel> questionModels = resp.getQuestionModels();
		List<String> expectedAnswers = Arrays.asList("cap1", "cap2", "cap3", "cap4", "cap5");
		Map<Integer, Double> weakExpectedOptionValues = createOptionValueWith(0, 1, 1, 1, 1);
		Map<Integer, Double> moderateExpectedOptionValues = createOptionValueWith(0, 0, 0.5, 1, 1);
		Map<Integer, Double> elementaryExpectedOptionValues = createOptionValueWith(0, 0, 0, 0.5, 1);

		assertEquals(3, questionModels.size());

		QuestionModel questionModel = questionModels.get(2);
		assertEquals(resp.getQuestionnaireModels().get(QUESTIONNAIRE_DEV_OPS_INDEX).getCode(), questionModel.getQuestionnaireCode());
		assertEquals("metric question test m1?", questionModel.getTitle());

		List<String> actualAnswers = questionModel.getAnswers().stream().map(AnswerModel::getCaption).collect(Collectors.toList());
		assertEquals(expectedAnswers, actualAnswers);
		assertFalse(questionModel.isMayNotBeApplicable());

		String attributeTeamReflectionCode = resp.getAttributeModels().get(ATTRIBUTE_TEAM_REFLECTION_INDEX).getCode();

		ImpactModel impactModel1 = questionModel.getQuestionImpacts().get(0);
		assertEquals(attributeTeamReflectionCode, impactModel1.getAttributeCode());
		assertEquals(resp.getLevelModels().get(LEVEL_WEAK_INDEX).getTitle(), impactModel1.getLevel().getTitle());
		assertTrue(Maps.difference(weakExpectedOptionValues, impactModel1.getOptionValues()).areEqual());
		assertEquals(1, impactModel1.getWeight());

		ImpactModel impactModel2 = questionModel.getQuestionImpacts().get(1);
		assertEquals(attributeTeamReflectionCode, impactModel2.getAttributeCode());
		assertEquals(resp.getLevelModels().get(LEVEL_MODERATE_INDEX).getTitle(), impactModel2.getLevel().getTitle());
		assertTrue(Maps.difference(moderateExpectedOptionValues, impactModel2.getOptionValues()).areEqual());
		assertEquals(2, impactModel2.getWeight());

		ImpactModel impactModel3 = questionModel.getQuestionImpacts().get(2);
		assertEquals(attributeTeamReflectionCode, impactModel3.getAttributeCode());
		assertEquals(resp.getLevelModels().get(LEVEL_ELEMENTARY_INDEX).getTitle(), impactModel3.getLevel().getTitle());
		assertTrue(Maps.difference(elementaryExpectedOptionValues, impactModel3.getOptionValues()).areEqual());
		assertEquals(3, impactModel3.getWeight());
	}

	private Map<Integer, Double> createOptionValueWith(double... values) {
		Map<Integer, Double> optionValues = new HashMap<>();
		int i = 1;
		for (double value : values) {
			optionValues.put(i++, value);
		}
		return optionValues;
	}

	private String readDslContent() {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/dsl-sample/sampleV2.ak"));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
