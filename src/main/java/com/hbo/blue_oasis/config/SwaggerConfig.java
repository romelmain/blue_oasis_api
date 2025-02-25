package com.hbo.blue_oasis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(info = @Info(title = "BLUE OASIS API", description = "Our app provides a concise listing of football team names", termsOfService = "www.aa.com/terminos_y_condiciones", version = "1.0.0", contact = @Contact(name = "Romel Herrera", url = "https://aa.com", email = "santy@mail.com"), license = @License(name = "Standard Software Use License for Romelxxx", url = "www.aaa.com/licence")), servers = {
        @Server(description = "DEV SERVER", url = "http://localhost:8080"),
        @Server(description = "PROD SERVER", url = "http://aaae:8080")
}, security = @SecurityRequirement(name = "Security Token"))
@SecurityScheme(name = "Security Token", description = "Access Token For My API", type = SecuritySchemeType.HTTP, paramName = HttpHeaders.AUTHORIZATION, in = SecuritySchemeIn.HEADER, scheme = "bearer", bearerFormat = "JWT")
public class SwaggerConfig {
}
