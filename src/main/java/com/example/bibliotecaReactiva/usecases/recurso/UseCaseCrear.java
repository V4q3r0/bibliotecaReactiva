package com.example.bibliotecaReactiva.usecases.recurso;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.collects.Recurso;
import com.example.bibliotecaReactiva.mappers.RecursoMapper;
import com.example.bibliotecaReactiva.repositorys.RecursoRepository;
import com.example.bibliotecaReactiva.usecases.Guardar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseCrear implements Guardar {
    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;
    @Autowired
    public UseCaseCrear(RecursoMapper recursoMapper, RecursoRepository recursoRepository){
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> apply(RecursoDTO recursoDTO) {
        return recursoRepository.save(recursoMapper.dtoToRecurso(null).apply(recursoDTO)).map(Recurso::getId);
    }
}
