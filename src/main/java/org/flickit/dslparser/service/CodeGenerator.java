package org.flickit.dslparser.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class CodeGenerator {

    @Value("${code_file_path}")
    private String codeFilePath;

    public String generate() {
        String newCode = generateNewCode();
        saveNewCodeToFile(newCode);
        return newCode;
    }

    private String generateNewCode() {
        Long lastValue = readLastCodeFromFile();
        if (lastValue == null) {
            lastValue = Long.valueOf(0);
        }
        lastValue++;
        String code = String.format("%010d", lastValue);
        saveNewCodeToFile(String.valueOf(lastValue));
        return code;
    }

    private Long readLastCodeFromFile() {
        try {
            File file = new File(codeFilePath);
            if (file.exists()) {
                return Long.valueOf(FileUtils.readFileToString(file, Charset.defaultCharset()));
            }
        } catch (IOException e) {
            // handle exception
        }
        return null;
    }



    private void saveNewCodeToFile(String newCode) {
        try {
            FileUtils.write(new File(codeFilePath), newCode, Charset.defaultCharset());
        } catch (IOException e) {
            // handle exception
        }
    }

}
