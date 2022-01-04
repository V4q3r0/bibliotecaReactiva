package com.example.bibliotecaReactiva.routers;

import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import com.example.bibliotecaReactiva.usecases.recurso.UseCaseBorrar;
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
@ContextConfiguration(classes = {BorrarRecursoRouter.class, UseCaseBorrar.class, RecursoMapper.class})
class BorrarRecursoRouterTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void borrarRecursoTest(){
        when(recursoRepository.deleteById("01")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/delete/{id}", "01")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

}