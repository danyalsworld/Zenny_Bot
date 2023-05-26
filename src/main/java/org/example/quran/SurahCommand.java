package org.example.quran;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SurahCommand {

    private static final String API_ENDPOINT_FORMAT = "http://api.alquran.cloud/v1/surah/%d/en.asad";

    public SurahCommand() {}

    public String surahGet(String surah) throws IOException, InterruptedException {
        int surahToGet = Integer.parseInt(surah.split(" ")[1]);
        String endpoint = String.format(API_ENDPOINT_FORMAT, surahToGet);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        int statusCode = response.statusCode();

        System.out.println("Status code: " + statusCode);

        return parseJsonResponse(responseBody);
    }

    private String parseJsonResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        JsonNode ayahsNode = jsonNode.get("data").get("ayahs");
        StringBuilder textFields = new StringBuilder();

        for (JsonNode ayahNode : ayahsNode) {
            String text = ayahNode.get("text").asText();
            System.out.println(text);
            textFields.append("\n").append(text);
        }

        return textFields.toString();
    }
}
