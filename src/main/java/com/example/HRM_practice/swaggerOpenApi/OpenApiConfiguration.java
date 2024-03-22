package com.example.HRM_practice.swaggerOpenApi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(title = "HRM PRACTICE API",
                description = "HRM PRACTICE APPLICATION INTERFACE",
                version = "v1.0.0"))
@Configuration
public class OpenApiConfiguration {

    private static Logger log = LoggerFactory.getLogger(OpenApiConfiguration.class);

//    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri:#{null}}")
//    private String jwksUri;
//    @Value("${meig.oauth2.token_url:#{null}}")
//    private String tokenUrl;

    public OpenApiCustomiser customiser(){
        return openApi -> {
            log.info("configure openapi documents");
            SwaggerUtil.addCommon(openApi);
            // add security requirement
//            if (jwksUri != null && tokenUrl != null)
//                SwaggerUtil.addOAuth2ClientCredentials(openApi, tokenUrl);

        };
    }



}
