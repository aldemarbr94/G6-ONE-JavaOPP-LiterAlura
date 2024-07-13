package com.aluracursos.literaluraSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Autores {

    private String nombre;
    private Integer anioDeNacimiento;
    private Integer anioDeFallecimiento;
    private List<String> libros;



    //Constructor
    public Autores(Libro libro, List<String> libros){
        this.nombre = libro.getAutor();
        this.anioDeNacimiento = libro.getAnioDeNacimiento();
        this.anioDeFallecimiento = libro.getAnioDeFallecimiento();
        this.libros = libros;
    }



    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<String> getLibros() {
        return libros;
    }

    public void setLibros(List<String> libros) {
        this.libros = libros;
    }


    @Override
    public String toString() {
        return "\n\nAutor: " + nombre +
                "\nFecha de nacimiento: " + anioDeNacimiento +
                "\nFecha de fallecimiento: " + anioDeFallecimiento +
                "\nLibros: " + libros;
    }
}
