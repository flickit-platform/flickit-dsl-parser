package org.flickit.dslparser.service.xtextv2;


import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.flickit.dsl.editor.v2.AssessmentKitDslStandaloneSetup;
import org.flickit.dslparser.service.xtext.ResourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Slf4j
public class ResourceServiceV2 extends ResourceService {

    @Value("${dsl_file_path}")
    private String dslFilePath;

    @Override
    public Resource setupResource(String dslContent) {
        Path path = writeDslContent(dslContent);
        Injector injector = new AssessmentKitDslStandaloneSetup().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
        Resource resource = resourceSet.getResource(URI.createFileURI(path.toString()), true);
        deleteDslFile(path);
        return resource;
    }
}
