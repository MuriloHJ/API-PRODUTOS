package com.br.apiprodutos.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig
{
    final String schemeName = "bearerAuth";

    @Bean
    public OpenAPI customOpemAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("Api de Gerenciamento de Produtos")
                .version("1.0")
                .description("API RESTful devenvolvida para controle, cadastro e manutenção do catálogo de produtos")
                .contact(new Contact()
                        .name("Suporte Técnico")
                        .email("contato@aula.com.br")
                )

                )
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                .components(new Components()
                        .addSecuritySchemes(schemeName,
                                new SecurityScheme()
                                        .name(schemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));

    }
}
