package org.flickit.dslparser.controller.exception;

import org.flickit.dslparser.controller.exception.api.ErrorCodes;
import org.flickit.dslparser.controller.exception.api.SyntaxError;
import org.flickit.dslparser.controller.exception.api.SyntaxErrorResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.flickit.dslparser.common.Message.NOT_FOUND_FILE_NAME_MESSAGE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class DSLHasSyntaxErrorExceptionHandlerTest {

    @LocalServerPort
    private int port;

    @Test
    void extractSyntaxErrorsTest() {
        String dslContent = readDslContent("sampleHasSyntaxError.ak");
        HashMap<String, String> request = new HashMap<>();
        request.put("dslContent", dslContent);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/extract",
                request,
                Object.class
        );
        SyntaxErrorResponseDto syntaxErrorResponseDto = mapToResponseDto(response.getBody());

        List<SyntaxError> errors = syntaxErrorResponseDto.errors();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals(ErrorCodes.SYNTAX_ERROR, syntaxErrorResponseDto.message());

        assertEquals(14, errors.size());
        for (SyntaxError syntaxError : errors) {
            assertThat(syntaxError.message(), is(not(emptyOrNullString())));
            assertThat(syntaxError.fileName(), is(not(emptyOrNullString())));
            assertThat(syntaxError.fileName(), is(not(NOT_FOUND_FILE_NAME_MESSAGE)));
            assertThat(syntaxError.fileName(), containsString(".ak"));
            assertThat(syntaxError.errorLine(), is(not(emptyOrNullString())));
            assertThat(syntaxError.line(), is(greaterThan(0)));
            assertThat(syntaxError.column(), is(greaterThan(0)));
        }

        SyntaxError error = errors.get(0);
        assertThat(error.message(), is(equalTo("mismatched input 'index:' expecting 'value:'")));
        assertThat(error.fileName(), is(equalTo("levels.ak")));
        assertThat(error.errorLine(), is(equalTo("    index: 1\r")));
        assertThat(error.line(), is(equalTo(10)));
        assertThat(error.column(), is(equalTo(5)));
    }

    @Test
    void extractCustomSyntaxErrorsTest() {
        String dslContent = readDslContent("sampleHasCustomSyntaxError.ak");
        HashMap<String, String> request = new HashMap<>();
        request.put("dslContent", dslContent);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/extract",
                request,
                Object.class
        );
        SyntaxErrorResponseDto syntaxErrorResponseDto = mapToResponseDto(response.getBody());

        List<SyntaxError> errors = syntaxErrorResponseDto.errors();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals(ErrorCodes.SYNTAX_ERROR, syntaxErrorResponseDto.message());

        assertEquals(4, errors.size());
        for (SyntaxError syntaxError : errors) {
            assertThat(syntaxError.message(), is(not(emptyOrNullString())));
            assertThat(syntaxError.fileName(), is(not(emptyOrNullString())));
            assertThat(syntaxError.fileName(), is(not(NOT_FOUND_FILE_NAME_MESSAGE)));
            assertThat(syntaxError.fileName(), containsString(".ak"));
            assertThat(syntaxError.errorLine(), is(not(emptyOrNullString())));
            assertThat(syntaxError.line(), is(greaterThan(0)));
            assertThat(syntaxError.column(), is(greaterThan(0)));
        }

        SyntaxError error = errors.get(0);
        assertThat(error.message(), is(equalTo("'Title' may not be empty!")));
        assertThat(error.fileName(), is(equalTo("levels.ak")));
        assertThat(error.errorLine(), is(equalTo("    title:\"\"\r")));
        assertThat(error.line(), is(equalTo(8)));
        assertThat(error.column(), is(equalTo(11)));
    }

    @Test
    void extractParserErrorsTest() {
        String dslContent = readDslContent("sampleWithSubjectWithoutAttributeError.ak");
        HashMap<String, String> request = new HashMap<>();
        request.put("dslContent", dslContent);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/extract",
                request,
                Object.class
        );
        SyntaxErrorResponseDto syntaxErrorResponseDto = mapToResponseDto(response.getBody());

        List<SyntaxError> errors = syntaxErrorResponseDto.errors();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals(ErrorCodes.SYNTAX_ERROR, syntaxErrorResponseDto.message());

        assertEquals(1, errors.size());

        SyntaxError error = errors.get(0);
        assertThat(error.message(), is(equalTo("Subject with [security] id should have at least one Attribute")));
    }

    private SyntaxErrorResponseDto mapToResponseDto(Object responseBody) {
        LinkedHashMap<String, Object> body = (LinkedHashMap) responseBody;
        String message = String.valueOf(body.get("message"));

        List<LinkedHashMap<String, Object>> errors = (List<LinkedHashMap<String, Object>>) (body.get("errors"));
        List<SyntaxError> syntaxErrors = errors.stream().map(e -> new SyntaxError(
                String.valueOf(e.get("message")),
                String.valueOf(e.get("fileName")),
                String.valueOf(e.get("errorLine")),
                (Integer) e.get("line"),
                (Integer) e.get("column"))
        ).collect(Collectors.toList());

        return new SyntaxErrorResponseDto(message, syntaxErrors);
    }

    private String readDslContent(String fileName) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/dsl-sample/" + fileName));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
