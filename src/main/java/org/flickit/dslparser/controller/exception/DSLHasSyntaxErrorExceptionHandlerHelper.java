package org.flickit.dslparser.controller.exception;

import org.eclipse.xtext.validation.Issue;
import org.flickit.dslparser.controller.exception.api.SyntaxError;
import org.flickit.dslparser.service.exception.DSLHasSyntaxErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DSLHasSyntaxErrorExceptionHandlerHelper {

    public static final String NOT_FOUND_FILE_NAME_NAME = "NOT_DETERMINED";

    @Value("${app.dsl-content.file-start-line-pattern}")
    private String fileStartLinePattern;

    @Value("${app.dsl-content.file-name-pattern}")
    private String fileNamePattern;

    public List<SyntaxError> extractErrors(DSLHasSyntaxErrorException ex) {
        List<Issue> issues = ex.getErrors();
        LinkedHashSet<SyntaxError> errors = new LinkedHashSet<>();

        ArrayList<Integer> filesStartLine = extractFilesStartLine(ex.getDslContent());
        String[] dslLines = ex.getDslContent().split("\n");
        for (Issue issue : issues) {
            FileErrorLine fileErrorLine = extractError(issue.getLineNumber(), filesStartLine, dslLines);
            SyntaxError error = new SyntaxError(issue.getMessage(),
                    fileErrorLine.getFileName(),
                    fileErrorLine.getLine(),
                    issue.getColumn());
            errors.add(error);
        }
        return new ArrayList<>(errors);
    }

    private ArrayList<Integer> extractFilesStartLine(String dslContent) {
        Matcher fileNameMather = Pattern.compile(fileStartLinePattern).matcher(dslContent);
        Pattern breakLinePattern = Pattern.compile("\\n");

        ArrayList<Integer> filesStartLine = new ArrayList<>();
        while (fileNameMather.find()) {
            int fileStartIndex = dslContent.indexOf(fileNameMather.group());
            int fileStartLineNumber = (int) breakLinePattern.matcher(dslContent.substring(0, fileStartIndex)).results().count();
            filesStartLine.add(fileStartLineNumber);
        }
        return filesStartLine;
    }

    private FileErrorLine extractError(int line, ArrayList<Integer> filesStartLine, String[] dslLines) {
        int indexOfFile = (int) (filesStartLine.stream().filter(l -> l < line).count()) - 1;
        Integer fileStartLine = filesStartLine.get(indexOfFile);
        int errorLineInFile = line - fileStartLine - 1;
        String lineIncludedFileName = dslLines[fileStartLine];
        Matcher fileNameMather = Pattern.compile(fileNamePattern).matcher(lineIncludedFileName);
        String fileName;
        if (fileNameMather.find()) {
            fileName = fileNameMather.group();
        } else {
            fileName = NOT_FOUND_FILE_NAME_NAME;
        }
        return new FileErrorLine(fileName, errorLineInFile);
    }

    @lombok.Value
    class FileErrorLine {
        String fileName;
        int line;
    }
}
