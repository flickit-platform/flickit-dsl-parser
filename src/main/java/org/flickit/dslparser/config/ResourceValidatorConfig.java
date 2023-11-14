package org.flickit.dslparser.config;

import com.google.inject.Injector;
import org.eclipse.xtext.validation.IResourceValidator;
import org.flickit.dsl.editor.v2.AssessmentKitDslStandaloneSetup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceValidatorConfig {

    @Bean
    public IResourceValidator resourceValidator() {
        AssessmentKitDslStandaloneSetup assessmentKitDslStandaloneSetup = new AssessmentKitDslStandaloneSetup();
        Injector injector = assessmentKitDslStandaloneSetup.createInjectorAndDoEMFRegistration();
        return injector.getInstance(IResourceValidator.class);
    }
}
