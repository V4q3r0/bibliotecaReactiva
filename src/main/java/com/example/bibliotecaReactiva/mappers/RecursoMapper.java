package com.example.bibliotecaReactiva.mappers;

import com.example.bibliotecaReactiva.DTOs.RecursoDTO;
import com.example.bibliotecaReactiva.collects.Recurso;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RecursoMapper {

    public Function<RecursoDTO, Recurso> dtoToRecurso(String id){
        return updateRecurso -> {
            var recurso = new Recurso();
            recurso.setId(id);
            recurso.setName(updateRecurso.getName());
            recurso.setTipo(updateRecurso.getTipo());
            recurso.setTematica(updateRecurso.getTematica());
            recurso.setEstado(updateRecurso.isEstado());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> recursoToRecursoDTO() {
        return entity -> new RecursoDTO(
                entity.getId(),
                entity.getName(),
                entity.getTipo(),
                entity.getTematica(),
                entity.isEstado()
        );
    }
}
