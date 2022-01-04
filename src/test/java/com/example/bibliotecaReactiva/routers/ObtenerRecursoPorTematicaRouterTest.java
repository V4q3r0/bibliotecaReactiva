package com.example.bibliotecaReactiva.routers;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.collects.Recurso;
import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import com.example.bibliotecaReactiva.usecases.recurso.UseCaseListarPorTematica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ObtenerRecursoPorTematicaRouter.class, UseCaseListarPorTematica.class, RecursoMapper.class})
class ObtenerRecursoPorTematicaRouterTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void obtenerPorTemeticaTest(){

        Recurso recurso1 = new Recurso();
        recurso1.setId("01");
        recurso1.setName("Los 7 pecados capitales");
        recurso1.setTipo("Libro");
        recurso1.setTematica("Ciencia ficción");
        recurso1.setEstado(false);

        Recurso recurso2 = new Recurso();
        recurso2.setId("02");
        recurso2.setName("Fallen angel caído");
        recurso2.setTipo("Libro");
        recurso2.setTematica("Ciencia ficción");
        recurso2.setEstado(false);

        when(recursoRepository.findByTematica(recurso1.getTematica())).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recurso/tematica/{tematica}", "Ciencia ficción")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(recursoDTOS -> {
                    Assertions.assertEquals(recursoDTOS.get(0).getTematica(), recurso1.getTematica());
                    Assertions.assertEquals(recursoDTOS.get(1).getTematica(), recurso2.getTematica());
                });

    }

}