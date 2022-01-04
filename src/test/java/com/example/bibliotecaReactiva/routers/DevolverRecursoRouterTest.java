package com.example.bibliotecaReactiva.routers;

import com.example.bibliotecaReactiva.collects.Recurso;
import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import com.example.bibliotecaReactiva.usecases.recurso.UseCaseDevolverRecurso;
import com.example.bibliotecaReactiva.usecases.recurso.UseCasePrestarRecurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DevolverRecursoRouter.class, UseCaseDevolverRecurso.class, RecursoMapper.class})
class DevolverRecursoRouterTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void devolverRecursoTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("01");
        recurso1.setName("Los 7 pecados capitales");
        recurso1.setTipo("Libro");
        recurso1.setTematica("Ciencia ficción");
        recurso1.setEstado(false);

        when(recursoRepository.findById(recurso1.getId())).thenReturn(Mono.just(recurso1));

        webTestClient.get()
                .uri("/recurso/devolver/{id}", "01")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(recursoDTO -> {
                    Assertions.assertEquals(recursoDTO, "El recurso no ha sido prestado.");
                });
    }

}