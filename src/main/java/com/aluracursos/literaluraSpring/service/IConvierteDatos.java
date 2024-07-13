package com.aluracursos.literaluraSpring.service;

public interface IConvierteDatos {

    //Metodo que va a permitir deserializar un archivo 'json'
    <T> T obtenerDatos(String json, Class<T> clase);

}
