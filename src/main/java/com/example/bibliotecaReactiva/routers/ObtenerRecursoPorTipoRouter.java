package com.example.bibliotecaReactiva.routers;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.usecases.recurso.UseCaseListarPorTipo;
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
public class ObtenerRecursoPorTipoRouter {

    @Bean
    public RouterFunction<ServerResponse> getRecursoByTipo(UseCaseListarPorTipo useCaseListarPorTipo){
        return route(
                GET("/recurso/tipo/{tipo}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseListarPorTipo.apply(
                                request.pathVariable("tipo")),
                                RecursoDTO.class
                        ))
        );
    }
}
