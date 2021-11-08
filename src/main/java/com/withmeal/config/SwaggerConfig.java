package com.withmeal.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * created by Gyunny 2021/11/09
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiInfo apiInfo() {
        var applicationName = "With Meal";
        return new ApiInfoBuilder()
                .title(applicationName)
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "accessToken", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().
                securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        var authorizationScopes = new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")};
        return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
    }
}
