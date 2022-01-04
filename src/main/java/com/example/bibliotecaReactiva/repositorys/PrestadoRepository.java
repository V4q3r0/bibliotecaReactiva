package com.example.bibliotecaReactiva.repositorys;

import com.example.bibliotecaReactiva.collects.Prestado;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PrestadoRepository extends ReactiveMongoRepository<Prestado, String> {
    Mono<Prestado> findByIdRecurso(String id);
}
