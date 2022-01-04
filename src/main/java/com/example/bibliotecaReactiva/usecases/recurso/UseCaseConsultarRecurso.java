package com.example.bibliotecaReactiva.usecases.recurso;

import com.example.bibliotecaReactiva.repositorys.PrestadoRepository;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class UseCaseConsultarRecurso implements Function<String, Mono<String>> {

    private final RecursoRepository recursoRepository;
    private final PrestadoRepository prestadoRepository;

    public UseCaseConsultarRecurso(RecursoRepository recursoRepository, PrestadoRepository prestadoRepository){
        this.recursoRepository = recursoRepository;
        this.prestadoRepository = prestadoRepository;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "Id es requerido");
        var recurso = recursoRepository.findById(id);
        return recurso.flatMap(recurso1 -> {
            if(recurso1.isEstado()){
                return Mono.just("El recurso NO está disponible.");
            }
            return Mono.just("El recurso está disponible.");
        });
    }
}
