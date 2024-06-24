package lt.viko.eif.l.jurgutis.editor_REST.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Lukas Jurgutis",
                        email = "lukas.jurgutis@ad.viko.lt",
                        url= "https://gmail.com"),
                description = "REST API for Editor Service.",
                title = "Editor Service REST",
                version = "1.0.0",
                license = @License(
                        name = "Free To Use",
                        url = "https://ad.viko.lt")
        ),
        servers = {
                @Server(
                        url = "http://localhost:8082",
                        description = "Local"
                )
        }
)
public class config {
}
