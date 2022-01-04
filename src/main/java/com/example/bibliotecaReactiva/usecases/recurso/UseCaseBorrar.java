package com.example.bibliotecaReactiva.usecases.recurso;

import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.PrestadoRepository;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class UseCaseBorrar implements Function<String, Mono<Void>> {
    private final RecursoRepository recursoRepository;
    private final PrestadoRepository prestadoRepository;

    public UseCaseBorrar(RecursoRepository recursoRepository, PrestadoRepository prestadoRepository){
        this.recursoRepository = recursoRepository;
        this.prestadoRepository = prestadoRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Id requerida");
        return recursoRepository.deleteById(id);
    }
}
