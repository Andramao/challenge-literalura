package com.andramao.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDatos {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T convertirDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar el JSON: " + e.getMessage());
        }
    }
}
