package com.td005.jobportal.config;

import jakarta.persistence.Entity;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /* * Dosya sistemindeki bir dizindeki dosyalari sunmak icin egik cizgi fotograflarini yonelik istedgi eslestirecek /photos*/

    private static final String UPLOAD_DIR = "photos";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(UPLOAD_DIR , registry );

    }

    private void exposeDirectory(String UPLOAD_DIR , ResourceHandlerRegistry registry)
    {
        Path path = Paths.get(UPLOAD_DIR);
        registry.addResourceHandler("/"+UPLOAD_DIR + "/**").addResourceLocations("file:"+path.toAbsolutePath()+"/");
        
    }
}
