package com.redolfi.demolfi.config;

import com.redolfi.demolfi.entities.Reserva;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config, CorsRegistry cors) {

        config.exposeIdsFor(Reserva.class); // Expone el ID de Reserva
        config.getExposureConfiguration()
                .forDomainType(Reserva.class)
                .withItemExposure((metadata, httpMethods) ->
                        httpMethods.disable(HttpMethod.PATCH));
    }
}
