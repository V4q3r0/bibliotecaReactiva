package com.example.bibliotecaReactiva.usecases.recurso;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class UseCaseListarOne implements Function<String, Mono<RecursoDTO>> {
    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public UseCaseListarOne(RecursoRepository recursoRepository, RecursoMapper recursoMapper){
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<RecursoDTO> apply(String id) {
        return recursoRepository.findById(id)
                .map(recursoMapper.recursoToRecursoDTO());
    }
}
