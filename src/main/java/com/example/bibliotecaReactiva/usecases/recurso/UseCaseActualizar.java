package com.example.bibliotecaReactiva.usecases.recurso;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.collects.Recurso;
import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import com.example.bibliotecaReactiva.usecases.Guardar;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UseCaseActualizar implements Guardar {
    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public UseCaseActualizar(RecursoRepository recursoRepository, RecursoMapper recursoMapper){
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> apply(RecursoDTO recursoDTO) {
        Objects.requireNonNull(recursoDTO.getId(), "Id es requerida");
        return recursoRepository
                .save(recursoMapper.dtoToRecurso(recursoDTO.getId()).apply(recursoDTO))
                .map(Recurso::getId);
    }
}
