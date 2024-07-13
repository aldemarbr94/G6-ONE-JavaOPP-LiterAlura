package com.aluracursos.literaluraSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String titulo;
    private String autor;
    private Integer anioDeNacimiento;
    private Integer anioDeFallecimiento;
    private String lenguajes;
    private Double numeroDeDescargas;


    public Libro() {
    } //constructor predeterminado


    //Constructor
    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.autor = datosLibros.autores().stream().map(a -> a.nombre()).collect(Collectors.joining());
        this.anioDeNacimiento = datosLibros.autores().stream()
                .map(a -> a.anioDeNacimiento())
                .findFirst().orElse(0);
        this.anioDeFallecimiento = datosLibros.autores().stream()
                .map(a -> a.anioDeFallecimiento())
                .findFirst().orElse(0);
        this.lenguajes = datosLibros.lenguajes().stream()
                .findFirst()
                .orElse("sin idioma");
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }


    //Getters y Setters
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnioDeNacimiento() {
        return anioDeNacimiento;
    }

    public void setAnioDeNacimiento(Integer anioDeNacimiento) {
        this.anioDeNacimiento = anioDeNacimiento;
    }

    public Integer getAnioDeFallecimiento() {
        return anioDeFallecimiento;
    }

    public void setAnioDeFallecimiento(Integer anioDeFallecimiento) {
        this.anioDeFallecimiento = anioDeFallecimiento;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }



    @Override
    public String toString() {
        return "\n\nTitulo: " + titulo +
                "\nAutor: " + autor +
                "\nIdioma: " + lenguajes +
                "\nNumero de descargas: " + numeroDeDescargas;
    }
}
