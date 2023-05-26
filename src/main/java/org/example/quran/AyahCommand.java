package org.example.quran;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AyahCommand {

    private final HttpClient httpClient;

    public AyahCommand() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getArabicAyah(String ayah) throws JsonProcessingException {
        int ayahNumber = extractAyahNumber(ayah);
        String endpoint = String.format("http://api.alquran.cloud/v1/ayah/%d", ayahNumber);
        return sendHttpRequestAndGetAyahText(endpoint);
    }

    public String getEnglishAyah(String ayah) throws JsonProcessingException {
        int ayahNumber = extractAyahNumber(ayah);
        String endpoint = String.format("http://api.alquran.cloud/v1/ayah/%d/en.asad", ayahNumber);
        return sendHttpRequestAndGetAyahText(endpoint);
    }

    private int extractAyahNumber(String ayah) {
        String[] parts = ayah.split(" ");
        return Integer.parseInt(parts[1]);
    }

    private String sendHttpRequestAndGetAyahText(String endpoint) throws JsonProcessingException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();

            // Check if the request was successful
            if (statusCode >= 200 && statusCode < 300) {
                return parseJsonAndGetAyahText(responseBody);
            } else {
                throw new RuntimeException("Failed to retrieve ayah. Status code: " + statusCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during the HTTP request.", e);
        }
    }

    private String parseJsonAndGetAyahText(String body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Ignore unknown properties
        QuranAyah quranAyah = objectMapper.readValue(body, QuranAyah.class);

        String ayahText = quranAyah.getData().getText();
        System.out.println("Ayah Text: " + ayahText);
        return ayahText;
    }
}

