package com.example.bibliotecaReactiva.routers;

import com.example.bibliotecaReactiva.usecases.recurso.UseCaseConsultarRecurso;
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
public class ComprobarRecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> comprobarRecurso(UseCaseConsultarRecurso useCaseConsultarRecurso){
        return route(
                GET("/comprobarRecurso/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromPublisher(useCaseConsultarRecurso.apply(request.pathVariable("id")), String.class))
        );
    }
}
