package org.flickit.dslparser.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@Slf4j
public class ResourceConfig {

    @Value("${dsl_file_path}")
    private String dslFilePath;

    @Bean
    void createResourceDirectory(){
        File dir = new File(dslFilePath);
        dir.mkdirs();
        if (!dir.exists())
            log.error("Error in creating dsl_file directory: {}", dslFilePath);
    }
}
