package com.andramao.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {
    @JsonAlias("title") String titulo;
    @JsonAlias("authors") List<DatosAutor> autor;
    @JsonAlias("languages") List<String> idiomas;
    @JsonAlias("download_count") Double numeroDeDescargas;

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<DatosAutor> getAutor() { return autor; }
    public void setAutor(List<DatosAutor> autor) { this.autor = autor; }

    public List<String> getIdiomas() { return idiomas; }
    public void setIdiomas(List<String> idiomas) { this.idiomas = idiomas; }

    public Double getNumeroDeDescargas() { return numeroDeDescargas; }
    public void setNumeroDeDescargas(Double numeroDeDescargas) { this.numeroDeDescargas = numeroDeDescargas; }

    @Override
    public String toString() {
        return "--- LIBRO ---" +
                "\nTítulo: " + titulo +
                "\nAutores: " + autor +
                "\nIdiomas: " + idiomas +
                "\nDescargas: " + numeroDeDescargas +
                "\n-------------";
    }
}