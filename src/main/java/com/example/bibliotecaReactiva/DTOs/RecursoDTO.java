package com.example.bibliotecaReactiva.DTOs;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RecursoDTO {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String tipo;
    @NotBlank
    private String tematica;
    @NotBlank
    private boolean estado;

    public RecursoDTO(String id, String name, String tipo, String tematica, boolean estado){
        this.id = id;
        this.name = name;
        this.tipo = tipo;
        this.tematica = tematica;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecursoDTO that = (RecursoDTO) o;
        return estado == that.estado && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(tipo, that.tipo) && Objects.equals(tematica, that.tematica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tipo, tematica, estado);
    }

    @Override
    public String toString() {
        return "RecursoDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tipo='" + tipo + '\'' +
                ", tematica='" + tematica + '\'' +
                ", estado=" + estado +
                '}';
    }
}
