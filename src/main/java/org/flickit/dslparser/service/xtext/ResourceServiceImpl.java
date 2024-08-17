package org.flickit.dslparser.service.xtext;


import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.flickit.dsl.editor.v2.AssessmentKitDslStandaloneSetup;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Slf4j
public class ResourceServiceImpl extends ResourceService {

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
