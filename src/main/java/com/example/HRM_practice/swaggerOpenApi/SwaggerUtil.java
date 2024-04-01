package com.example.HRM_practice.swaggerOpenApi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

public class SwaggerUtil {
    private SwaggerUtil() {
    }

    private static Logger log = LoggerFactory.getLogger(SwaggerUtil.class);

    // api

    public static void addCommon(OpenAPI api) {
        log.debug("configure openapi for {} paths", api.getPaths().size());
        // add common responses
        api.getPaths().forEach(SwaggerUtil::addCommonResponses);
        // add examples
        api.getPaths().forEach(SwaggerUtil::addExamples);
    }

    public static final String DEFAULT_OAUTH2_CLIENT_CREDENTIALS_NAME = "clientCredentials";

    public static void addOAuth2ClientCredentials(OpenAPI api, String tokenUrl) {
        addOAuth2ClientCredentials(api,
                DEFAULT_OAUTH2_CLIENT_CREDENTIALS_NAME, tokenUrl);
    }

    public static void addOAuth2ClientCredentials(
            OpenAPI api, String schemeName, String tokenUrl) {
        // add scheme
        log.debug("add security scheme");
        api.components(
                        api.getComponents().addSecuritySchemes(
                                schemeName,
                                createOAuth2ClientCredentials(tokenUrl)))
                .security(Collections.singletonList(
                        new SecurityRequirement().addList(schemeName)));
        // add error response
        api.getPaths().forEach(SwaggerUtil::addSecurityResponses);
    }

    public static SecurityScheme createOAuth2ClientCredentials(String tokenUrl) {
        return new SecurityScheme()
                .type(Type.OAUTH2)
                .flows(new OAuthFlows()
                        .clientCredentials(new OAuthFlow()
                                .tokenUrl(tokenUrl)));
    }

    // paths

    private static void applyOperations(PathItem path, Consumer<Operation> consumer) {
        consumer.accept(path.getGet());
        consumer.accept(path.getHead());
        consumer.accept(path.getPut());
        consumer.accept(path.getPost());
        consumer.accept(path.getDelete());
        consumer.accept(path.getPatch());
    }

    public static void addCommonResponses(String name, PathItem path) {
        if (path == null)
            return;
        log.debug("add common responses for path {}", name);
        applyOperations(path, SwaggerUtil::addCommonResponses);
    }

    public static void addExamples(String name, PathItem path) {
        if (path == null)
            return;
        log.debug("add examples for path {}", name);
        applyOperations(path, SwaggerUtil::addExamples);
    }

    public static void addSecurityResponses(String name, PathItem path) {
        if (path == null)
            return;
        log.debug("add security responses for path {}", name);
        applyOperations(path, SwaggerUtil::addSecurityResponses);
    }

    // operations

    public static void addCommonResponses(Operation op) {
        if (op == null)
            return;
        log.debug("add common responses for operation {}", op.getOperationId());
        op.getResponses().addApiResponse("400",
                new ApiResponse().description("Invalid request body"));
    }

    public static void addExamples(Operation op) {
        if (op == null)
            return;
        String opId = op.getOperationId();
        log.debug("add examples for operation {}", opId);
        // requests
        addExamples(opId, "req", op.getRequestBody().getContent());
        // responses
        op.getResponses().forEach((code, rsp)
                -> addExamples(opId, code, rsp.getContent()));
    }

    private static void addExamples(String opId, String item, Content content) {
        if (content == null)
            return;
        log.debug("add {} examples for operation {}", item, opId);
        content.entrySet().forEach(entry -> {
            String type = entry.getKey().replaceFirst(".*/", "");
            log.debug("add {} examples of type {} for operation {}", item, type, opId);
            Map<String, Example> examples = entry.getValue().getExamples();
            if (examples != null) {
                examples.forEach((name, example) -> {
                    log.debug("add {} example {} of type {} for operation {}", item, name, type, opId);
                    String text = loadExample(opId, item, name, type);
                    if (text != null) {
                        log.info("load {} example {} of type {} for operation {}: size {}",
                                item, name, type, opId, text.length());
                        example.setValue(text);
                    }
                });
            }
        });
    }

    private static String loadExample(String id, String item, String name, String type) {
        String path = "examples/" + id + '/' + item + '_' + name + '.' + type;
        ClassPathResource resource = new ClassPathResource(path);
        if (resource.isReadable()) {
            try (InputStream inputStream = resource.getInputStream()) {
                log.debug("input stream: {}", inputStream);
                String text = readString(inputStream);
                // remove line-trailing and -leading whitespaces
                if (type.equals("json"))
                    text = text.replaceAll("\\s*\\r?\\n\\s*", " ");
                return text;
            } catch (IOException e) {
                log.warn("load example file {} error", resource, e);
            }
        }
        return null;
    }

    private static String readString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }

    public static void addSecurityResponses(Operation op) {
        if (op == null)
            return;
        log.debug("add security responses for operation {}", op.getOperationId());
        op.getResponses().addApiResponse("401",
                new ApiResponse().description("Invalid token"));
    }
}

