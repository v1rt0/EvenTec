package com.eventec.eventec.repositories;

import com.eventec.eventec.models.Endereco;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Component
public class BrasilApiCepRepository implements CepRepository {

    private static final String BASE_URL = "https://brasilapi.com.br/api/cep/v2/";
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 10000;
    private final Gson gson = new Gson();
    private final Logger logger = LoggerFactory.getLogger(BrasilApiCepRepository.class);

    @Override
    public Endereco findByCep(String cep) {
        try {
            URL url = new URL(BASE_URL + cep);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                    return gson.fromJson(reader, Endereco.class);
                }
            } else {
                InputStream errorStream = connection.getErrorStream();
                String errorContent = "";
                if (errorStream != null) {
                    try (Scanner scanner = new Scanner(errorStream)) {
                        scanner.useDelimiter("\\Z");
                        errorContent = scanner.next();
                        logger.error("Erro ao consultar CEP {}: {}", cep, errorContent);
                    }
                }
                throw new RuntimeException(String.format("Erro na resposta da API ao consultar CEP %s: %d - %s", cep, connection.getResponseCode(), errorContent));
            }
        } catch (Exception e) {
            logger.error("Erro ao buscar CEP " + cep, e);
            throw new RuntimeException("Erro ao buscar CEP " + cep, e);
        }
    }
}
