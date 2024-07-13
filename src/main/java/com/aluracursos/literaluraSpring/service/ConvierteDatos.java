package com.aluracursos.literaluraSpring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{

    private ObjectMapper objectMapper = new ObjectMapper();

    //Sobreescribir el metodo de la implementacion que va a permitir deserializar un archivo 'json'
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase); //readValue(String content, Class<T> valueType): Es un método de 'ObjectMapper' que lee una
            //cadena JSON (content) y la convierte en una instancia del tipo especificado por valueType
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
