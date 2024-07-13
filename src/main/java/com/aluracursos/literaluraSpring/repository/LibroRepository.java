package com.aluracursos.literaluraSpring.repository;

import com.aluracursos.literaluraSpring.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT DISTINCT l FROM Libro l")
    List<Libro> autoresRegistrados();

    @Query("SELECT l.titulo FROM Libro l WHERE l.autor = :autor")
    List<String> librosRegistradosPorAutor(String autor);

    @Query("SELECT l FROM Libro l WHERE l.anioDeFallecimiento > :anioUsuario AND l.anioDeNacimiento < :anioUsuario")
    List<Libro> autoresVivos(Integer anioUsuario);

    @Query("SELECT l FROM Libro l WHERE l.lenguajes = :lenguajeUsuario")
    List<Libro> lenguajeLibros(String lenguajeUsuario);



}
