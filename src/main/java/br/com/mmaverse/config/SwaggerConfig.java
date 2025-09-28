package br.com.mmaverse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Pedro");
        contact.setEmail("pedroantoniofla10@gmail.com");

        return new OpenAPI()
                .info(new Info()
                        .title("MMAverse API")
                        .contact(contact)
                        .version("1.0")
                        .description("API for gereniamento de eventos de MMA, lutadores, e mais..."));
    }
}
