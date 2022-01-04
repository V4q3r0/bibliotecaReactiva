package com.example.bibliotecaReactiva.repositorys;

import com.example.bibliotecaReactiva.collects.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String> {
    Flux<Recurso> findByTipo(String tipo);
    Flux<Recurso> findByTematica(String tematica);

}
