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
import java.util.*;
import java.util.stream.Collectors;

import static org.flickit.dslparser.controller.exception.DSLHasSyntaxErrorExceptionHandlerHelper.NOT_FOUND_FILE_NAME_NAME;
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
        String dslContent = readDslContent("sampleV2HasSyntaxError.ak");
        HashMap<String, String> request = new HashMap<>();
        request.put("dslContent", dslContent);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/extract",
                request,
                Object.class
        );
        SyntaxErrorResponseDto syntaxErrorResponseDto = mapToResponseDto(response.getBody());

        LinkedHashSet<SyntaxError> errors = syntaxErrorResponseDto.getErrors();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals(ErrorCodes.SYNTAX_ERROR, syntaxErrorResponseDto.getMessage());

        assertEquals(12, errors.size());
        for (SyntaxError syntaxError : errors) {
            assertThat(syntaxError.getMessage(), is(not(emptyOrNullString())));
            assertThat(syntaxError.getFileName(), is(not(emptyOrNullString())));
            assertThat(syntaxError.getFileName(), is(not(NOT_FOUND_FILE_NAME_NAME)));
            assertThat(syntaxError.getFileName(), containsString(".ak"));
            assertThat(syntaxError.getLine(), is(greaterThan(0)));
            assertThat(syntaxError.getColumn(), is(greaterThan(0)));
        }

        ArrayList<SyntaxError> errorList = new ArrayList<>(errors);
        SyntaxError error = errorList.get(0);
        assertThat(error.getMessage(), is(equalTo("mismatched input 'index:' expecting 'value:'")));
        assertThat(error.getFileName(), is(equalTo("levels.ak")));
        assertThat(error.getLine(), is(equalTo(8)));
        assertThat(error.getColumn(), is(equalTo(5)));
    }

    @Test
    void extractCustomSyntaxErrorsTest() {
        String dslContent = readDslContent("sampleV2HasCustomSyntaxError.ak");
        HashMap<String, String> request = new HashMap<>();
        request.put("dslContent", dslContent);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/extract",
                request,
                Object.class
        );
        SyntaxErrorResponseDto syntaxErrorResponseDto = mapToResponseDto(response.getBody());

        LinkedHashSet<SyntaxError> errors = syntaxErrorResponseDto.getErrors();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals(ErrorCodes.SYNTAX_ERROR, syntaxErrorResponseDto.getMessage());

        assertEquals(5, errors.size());
        for (SyntaxError syntaxError : errors) {
            assertThat(syntaxError.getMessage(), is(not(emptyOrNullString())));
            assertThat(syntaxError.getFileName(), is(not(emptyOrNullString())));
            assertThat(syntaxError.getFileName(), is(not(NOT_FOUND_FILE_NAME_NAME)));
            assertThat(syntaxError.getFileName(), containsString(".ak"));
            assertThat(syntaxError.getLine(), is(greaterThan(0)));
            assertThat(syntaxError.getColumn(), is(greaterThan(0)));
        }

        ArrayList<SyntaxError> errorList = new ArrayList<>(errors);
        SyntaxError error = errorList.get(0);
        assertThat(error.getMessage(), is(equalTo("'Title' may not be empty!")));
        assertThat(error.getFileName(), is(equalTo("levels.ak")));
        assertThat(error.getLine(), is(equalTo(7)));
        assertThat(error.getColumn(), is(equalTo(11)));
    }

    private SyntaxErrorResponseDto mapToResponseDto(Object responseBody) {
        LinkedHashMap<String, Object> body = (LinkedHashMap) responseBody;
        String message = String.valueOf(body.get("message"));

        List<LinkedHashMap<String, Object>> errors = (List<LinkedHashMap<String, Object>>) (body.get("errors"));
        List<SyntaxError> syntaxErrors = errors.stream().map(e -> new SyntaxError(
                String.valueOf(e.get("message")),
                String.valueOf(e.get("fileName")),
                (Integer) e.get("line"),
                (Integer) e.get("column"))
        ).collect(Collectors.toList());

        return new SyntaxErrorResponseDto(message, new LinkedHashSet<>(syntaxErrors));
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
