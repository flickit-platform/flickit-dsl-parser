package org.flickit.dslparser.service.xtext;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.emf.ecore.resource.Resource;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public abstract class ResourceService {

    @Value("${dsl_file_path}")
    private String dslFilePath;

    public abstract Resource setupResource(String dslContent);

    protected Path writeDslContent(String dslContent) {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        Path path = Path.of(dslFilePath + "//" + generatedString + ".ak");
        try {
            File file = new File(path.toString());
            file.createNewFile();
            Files.writeString(path, dslContent);
        } catch (Exception ex) {
            log.info("Error in writing dsl content into the file", ex);
        }
        return path;
    }

    protected void deleteDslFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException ex) {
            log.error("Error in delete dsl File");
        }
    }
}
