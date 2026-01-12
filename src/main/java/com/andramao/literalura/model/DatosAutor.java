package com.andramao.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosAutor {
    @JsonAlias("name") String nombre;
    @JsonAlias("birth_year") Integer fechaDeNacimiento;
    @JsonAlias("death_year") Integer fechaDeFallecimiento;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getFechaDeNacimiento() { return fechaDeNacimiento; }
    public void setFechaDeNacimiento(Integer fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }

    public Integer getFechaDeFallecimiento() { return fechaDeFallecimiento; }
    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) { this.fechaDeFallecimiento = fechaDeFallecimiento; }

    @Override
    public String toString() {
        return "Autor: " + nombre + " (" + fechaDeNacimiento + " - " + fechaDeFallecimiento + ")";
    }
}