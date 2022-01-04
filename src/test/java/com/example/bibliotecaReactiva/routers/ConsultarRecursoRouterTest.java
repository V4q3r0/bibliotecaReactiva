package com.example.bibliotecaReactiva.routers;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.collects.Recurso;
import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import com.example.bibliotecaReactiva.usecases.recurso.UseCaseListar;
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
@ContextConfiguration(classes = {ConsultarRecursoRouter.class, UseCaseListar.class, RecursoMapper.class})
class ConsultarRecursoRouterTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testListarRecursos() {
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

        when(recursoRepository.findAll()).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/consultar")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(recursoResponse -> {
                    Assertions.assertEquals(recursoResponse.get(0).getId(), recurso1.getId());
                    Assertions.assertEquals(recursoResponse.get(1).getId(), recurso2.getId());
                });
    }

}