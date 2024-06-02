package com.atividade.tde.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class SwaggerConfiguration
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/06/2024
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(this.info());
    }

    private Info info() {
        return new Info().title("TDE API")
                .description("TDE API REST service")
                .version("1.0.0")
                .contact(this.contact());
    }

    private Contact contact() {
        return new Contact()
                .name("João Sousa")
                .email("jsousa.quimica@gmail.com");
    }
}
