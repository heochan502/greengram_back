package com.green.greengram.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Slf4j
@Configuration //빈등록
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final String uploadDirectory;

    public WebMvcConfiguration(@Value("${constants.file.upload-directory}") String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
        log.info("Upload Path: {}", uploadDirectory);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:" + uploadDirectory);

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                  @Override
                  protected Resource getResource(String resourcePath, Resource location) throws IOException {
                      Resource resource = location.createRelative(resourcePath);

                      if(resource.exists() && resource.isReadable()) {
                          return resource;
                      }

                      return new ClassPathResource("/static/index.html");
                  }
                });
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true);

    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //RestController 애노테이션이 있는 컨트롤러의 주소앞에 /api를 붙여준다
        configurer.addPathPrefix("api", HandlerTypePredicate.forAnnotation(RestController.class));
    }

}
