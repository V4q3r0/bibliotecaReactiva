package com.example.bibliotecaReactiva.usecases;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface Guardar {
    public Mono<String> apply(RecursoDTO recursoDTO);
}
