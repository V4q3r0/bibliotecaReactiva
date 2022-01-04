package com.example.bibliotecaReactiva.usecases.recurso;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class UseCaseListarPorTipo implements Function<String, Flux<RecursoDTO>> {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public UseCaseListarPorTipo(RecursoRepository recursoRepository, RecursoMapper recursoMapper){
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Flux<RecursoDTO> apply(String tipo) {
        Objects.requireNonNull(tipo, "Tipo es requerido");
        return recursoRepository.findByTipo(tipo)
                .map(recursoMapper.recursoToRecursoDTO());
    }
}
