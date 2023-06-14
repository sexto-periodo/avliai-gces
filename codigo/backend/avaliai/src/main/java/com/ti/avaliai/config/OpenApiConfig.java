package com.ti.avaliai.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Avaliai",
                        url = "https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2023-1-ti5-5104100-avaliai"
                ),
                description = "API do Projeto AvaliAí",
                title = "AvaliAí Backend",
                version = "1.0",
                license = @License(
                        name = " CC-BY-4.0",
                        url = "https://creativecommons.org/licenses/by/4.0/"
                ),
                termsOfService = "Nossos termos de serviço"
        ),
        servers = {
                @Server(
                        description = "Ambiente Local",
                        url = "http://localhost:8080/avaliai"
                ),
                @Server(
                        description = "Ambiente de Produção",
                        url = "https://avaliai.web.app"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
