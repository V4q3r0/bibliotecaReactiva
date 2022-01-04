package com.example.bibliotecaReactiva.usecases.recurso;

import com.example.bibliotecaReactiva.collects.Prestado;
import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.PrestadoRepository;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class UseCasePrestarRecurso implements Function<String, Mono<String>> {

    private final RecursoRepository recursoRepository;
    private final PrestadoRepository prestadoRepository;

    public UseCasePrestarRecurso(RecursoRepository recursoRepository, PrestadoRepository prestadoRepository){
        this.recursoRepository = recursoRepository;
        this.prestadoRepository = prestadoRepository;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "Id es requerida");
        var recurso = recursoRepository.findById(id);
        return recurso.flatMap(recurso1 -> {
            if(!recurso1.isEstado()){
                Prestado prestado = new Prestado();
                prestado.setIdRecurso(recurso1.getId());
                prestado.setFechaEntrega(LocalDate.now().toString());
                recurso1.setEstado(true);
                recursoRepository.save(recurso1);
                prestadoRepository.save(prestado);
                return Mono.just("Haz prestado un recurso de la biblioteca.");
            }
            return Mono.just("El recurso no se encuentra disponible.");
        });
    }
}
