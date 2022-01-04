package com.example.bibliotecaReactiva.routers;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.usecases.recurso.UseCaseListarPorTematica;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ObtenerRecursoPorTematicaRouter {

    @Bean
    public RouterFunction<ServerResponse> getByTematica(UseCaseListarPorTematica useCaseListarPorTematica){
        return route(
                GET("/recurso/tematica/{tematica}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseListarPorTematica.apply(
                                request.pathVariable("tematica")),
                                RecursoDTO.class
                        ))
        );
    }
}
