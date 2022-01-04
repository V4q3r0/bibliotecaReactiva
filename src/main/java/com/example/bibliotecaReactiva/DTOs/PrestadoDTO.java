package com.example.bibliotecaReactiva.DTOs;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class PrestadoDTO {
    private String id;
    @NotBlank
    private String idRecurso;
    @NotBlank
    private String fechaEntrega;

    public PrestadoDTO(String id, String idRecurso, String fechaEntrega){
        this.id = id;
        this.idRecurso = idRecurso;
        this.fechaEntrega = fechaEntrega;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrestadoDTO that = (PrestadoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(idRecurso, that.idRecurso) && Objects.equals(fechaEntrega, that.fechaEntrega);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRecurso, fechaEntrega);
    }

    @Override
    public String toString() {
        return "PrestadoDTO{" +
                "id='" + id + '\'' +
                ", idRecurso='" + idRecurso + '\'' +
                ", fechaEntrega='" + fechaEntrega + '\'' +
                '}';
    }
}
