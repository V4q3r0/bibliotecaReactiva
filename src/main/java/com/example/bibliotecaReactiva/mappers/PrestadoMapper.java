package com.example.bibliotecaReactiva.mappers;

import com.example.bibliotecaReactiva.DTOs.PrestadoDTO;
import com.example.bibliotecaReactiva.collects.Prestado;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PrestadoMapper {

    public Function<PrestadoDTO, Prestado> dtoToPrestado(String id) {
        return updatePrestado -> {
            var prestado = new Prestado();
            prestado.setId(updatePrestado.getId());
            prestado.setIdRecurso(updatePrestado.getIdRecurso());
            prestado.setFechaEntrega(updatePrestado.getFechaEntrega());
            return prestado;
        };
    }

    public Function<Prestado, PrestadoDTO> prestadoToPrestadoDTO(){
        return entity -> new PrestadoDTO(entity.getId(), entity.getIdRecurso(), entity.getFechaEntrega());
    }
}
