package org.flickit.dslparser.service.xtext;


import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.flickit.dsl.editor.ProfileStandaloneSetup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Slf4j
public class ResourceService {

    @Value("${dsl_file_path}")
    private String dslFilePath;

    public Resource setupResource(String dslContent) {
        Path path = writeDslContent(dslContent);
        Injector injector = new ProfileStandaloneSetup().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
        Resource resource = resourceSet.getResource(URI.createFileURI(path.toString()), true);
        deleteDslFile(path);
        return resource;
    }


    private Path writeDslContent(String dslContent) {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        Path path = Path.of(dslFilePath + "//" + generatedString + ".profile");
        try {
            Files.writeString(path, dslContent);
        } catch (Exception ex) {
            log.info("Error in writing dsl content into the file", ex);
        }
        return path;
    }

    private void deleteDslFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException ex) {
            log.error("Error in delete dsl File");
        }
    }

}
