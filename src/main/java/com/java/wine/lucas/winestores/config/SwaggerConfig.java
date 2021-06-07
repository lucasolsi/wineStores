package com.java.wine.lucas.winestores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig
{
    Contact contact = new Contact("Lucas Oliveira Silva",
            "http://github.com/lucasolsi","lucasolsi@gmail.com");

    @Bean
    public Docket apiDocket()
    {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfo("Wine Stores","","1.0","",contact,"","",
                        new ArrayList<>()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.java.wine.lucas.winestores"))
                .paths(PathSelectors.any())
                .build();
    }
}
