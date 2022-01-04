package com.example.bibliotecaReactiva.routers;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.usecases.recurso.UseCaseActualizar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ActualizarRecursoRouter {

    /*@Bean
    public RouterFunction<ServerResponse> updateRecurso(UseCaseActualizar useCaseActualizar){
        return route(PUT("/recurso").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(recursoDTO -> useCaseActualizar.apply(recursoDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.TEXT_PLAIN)
                                        .bodyValue(result))
                        )
        );
    }*/
}
