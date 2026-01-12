package com.andramao.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    public String obtenerDatos(String url) {
        // 1. Construyendo el Cliente (HttpClient)
        HttpClient client = HttpClient.newHttpClient();

        // 2. Construyendo la Solicitud (HttpRequest)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            // 3. Construyendo la Respuesta (HttpResponse)
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Retornamos el cuerpo (JSON)
        return response.body();
    }
}