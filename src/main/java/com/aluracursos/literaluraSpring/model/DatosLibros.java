package com.aluracursos.literaluraSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")
        List<DatosAutores> autores,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("download_count") Double numeroDeDescargas) {

}
