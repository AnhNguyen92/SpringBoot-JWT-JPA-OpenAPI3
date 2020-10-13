package biz.idsoftware.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApi30Config {

    private final String moduleName;
    private final String apiVersion;

    public OpenApi30Config(@Value("${module.name}") String moduleName, @Value("${api.version}") String apiVersion) {
        this.moduleName = moduleName;
        this.apiVersion = apiVersion;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
        return new OpenAPI().servers(Arrays.asList(new Server().url("http://localhost:8080/bootdb"), new Server().url("http://localhost:8080/bootdb1")))
        		.addSecurityItem(new SecurityRequirement().addList(securitySchemeName)) // enable auth button
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info().title(apiTitle).version(apiVersion)
                		.description("This is a sample Book server created using springdocs - a library for OpenAPI 3 with spring boot.")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact().email("contact@idsolutions.com.vn").name("IDS Team"))
                        .license(new License().name("Apache 2.0")
                            .url("http://springdoc.org"))
                        .version("1.0"));
    }
    
}
