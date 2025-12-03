package db.start.reciclaalegre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customizarSwagger() {
        return new OpenAPI()
                .info(new Info()
                    .title("ReciclaAlegre API")
                    .version("1.0")
                    .description("Documentação da Api do ReciclaAlegre"));
    }
}