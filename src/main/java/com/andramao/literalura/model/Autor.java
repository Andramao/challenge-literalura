package com.andramao.literalura.model;

import com.andramao.literalura.DTO.DatosAutor;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {}
    public Autor(DatosAutor d) {
        this.nombre = d.nombre();
        this.fechaDeNacimiento = d.fechaDeNacimiento();
        this.fechaDeFallecimiento = d.fechaDeFallecimiento();
    }
    // Getters y Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {this.libros = libros;}


    public String getNombreFormateado() {
        if (this.nombre.contains(",")) {
            String[] partes = this.nombre.split(",");
            // "Miguel de" + " " + "Cervantes Saavedra"
            return partes[1].trim() + " " + partes[0].trim();
        }
        return this.nombre;
    }

    @Override
    public String toString() {
        // Extraemos solo los títulos de la lista de libros para mostrar en el reporte
        String titulosLibros = libros.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));

        return String.format(
                "\n*********** AUTOR ***********" +
                        "\nNombre: %s" +
                        "\nFecha de nacimiento: %s" +
                        "\nFecha de fallecimiento: %s" +
                        "\nLibros: [ %s ]" +
                        "\n*****************************",
                nombre,
                (fechaDeNacimiento != null ? fechaDeNacimiento : "Desconocido"),
                (fechaDeFallecimiento != null ? fechaDeFallecimiento : "Presente"),
                titulosLibros
        );
    }
}