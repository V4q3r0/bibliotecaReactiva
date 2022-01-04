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
public class UseCaseDevolverRecurso implements Function<String, Mono<String>> {

    private final RecursoRepository recursoRepository;
    private final PrestadoRepository prestadoRepository;

    public UseCaseDevolverRecurso(RecursoRepository recursoRepository, PrestadoRepository prestadoRepository){
        this.recursoRepository = recursoRepository;
        this.prestadoRepository = prestadoRepository;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "Id es requerida");
        var recurso = recursoRepository.findById(id);
        return recurso.flatMap(recurso1 -> {
            if(recurso1.isEstado()){
                var prestado = prestadoRepository.findByIdRecurso(id);
                recurso1.setEstado(false);
                prestadoRepository.deleteById(prestado.map(prestado1 -> prestado1.getId()));
                recursoRepository.save(recurso1);
                return Mono.just("Haz regresado el recurso a la biblioteca.");
            }
            return Mono.just("El recurso no ha sido prestado.");
        });
    }
}
