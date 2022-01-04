package com.example.bibliotecaReactiva.usecases.recurso;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class UseCaseListar implements Supplier<Flux<RecursoDTO>> {
    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public UseCaseListar(RecursoMapper recursoMapper, RecursoRepository recursoRepository){
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return recursoRepository.findAll().map(recursoMapper.recursoToRecursoDTO());
    }
}
