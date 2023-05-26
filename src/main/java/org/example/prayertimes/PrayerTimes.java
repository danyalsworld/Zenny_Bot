package org.example.prayertimes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrayerTimes {

    private HttpClient httpClient;
    public PrayerTimes() {
        this.httpClient=HttpClient.newHttpClient();
            }

    public Timings prayerTimeGet(String content) throws JsonProcessingException {
        Timings timings = null;
        String city;
        String country;
        String state;
        int year;
        PrayerResponse prayerResponse;
        String responseBody = null;
        ObjectMapper objectMapper = new ObjectMapper();
        String commandPrefix = ">>prayertimes"; // Example command prefix

        if (content.startsWith(commandPrefix)) {
            // Remove the command prefix from the content
            String command = content.substring(commandPrefix.length()).trim();
            String[] parts = command.split(",");
            if (parts.length == 4) {
                city = parts[0];
                country = parts[1];
                state = parts[2];
                year = Integer.parseInt(parts[3]);
            } else {
                city = parts[0];
                country = parts[1];
                year = Integer.parseInt(parts[2]);
            }

            String url = "http://api.aladhan.com/v1/calendarByCity/" + year + "/4?city=" + city + "&country=" + country;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                int statusCode = response.statusCode();
                responseBody = response.body();

                System.out.println("Status code: " + statusCode);
                System.out.println("Response body: " + responseBody);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                prayerResponse = objectMapper.readValue(responseBody, PrayerResponse.class);

                List<PrayerResponse.Data> dataList = prayerResponse.getData();
                if (dataList != null && !dataList.isEmpty()) {
                    PrayerResponse.Data data = dataList.get(0);
                    timings = data.getTimings();
                    System.out.println(timings.toString());


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return timings;
    }


}
