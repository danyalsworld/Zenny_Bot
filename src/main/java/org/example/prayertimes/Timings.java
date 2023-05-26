package org.example.prayertimes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timings {
    @JsonProperty("Fajr")
    private String fajr;

    @JsonProperty("Sunrise")
    private String sunrise;

    @JsonProperty("Dhuhr")
    private String dhuhr;

    @JsonProperty("Asr")
    private String asr;

    @JsonProperty("Sunset")
    private String sunset;

    @JsonProperty("Maghrib")
    private String maghrib;

    @JsonProperty("Isha")
    private String isha;

    @JsonProperty("Imsak")
    private String imsak;

    @JsonProperty("Midnight")
    private String midnight;

    @JsonProperty("Firstthird")
    private String firstthird;

    @JsonProperty("Lastthird")
    private String lastthird;

    // Getters and setters
    public Timings(){}

    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    public String getImsak() {
        return imsak;
    }

    public void setImsak(String imsak) {
        this.imsak = imsak;
    }

    public String getMidnight() {
        return midnight;
    }

    public void setMidnight(String midnight) {
        this.midnight = midnight;
    }

    public String getFirstthird() {
        return firstthird;
    }

    public void setFirstthird(String firstthird) {
        this.firstthird = firstthird;
    }

    public String getLastthird() {
        return lastthird;
    }

    public void setLastthird(String lastthird) {
        this.lastthird = lastthird;
    }


    public class PrayerTimings {
        @JsonProperty("Fajr")
        private String fajr;

        @JsonProperty("Sunrise")
        private String sunrise;

        @JsonProperty("Dhuhr")
        private String dhuhr;

        @JsonProperty("Asr")
        private String asr;

        @JsonProperty("Sunset")
        private String sunset;

        @JsonProperty("Maghrib")
        private String maghrib;

        @JsonProperty("Isha")
        private String isha;

        @JsonProperty("Imsak")
        private String imsak;

        @JsonProperty("Midnight")
        private String midnight;

        @JsonProperty("Firstthird")
        private String firstthird;

        @JsonProperty("Lastthird")
        private String lastthird;

        // Getters and setters
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("**Prayer Timings**\n");
        sb.append("Fajr: ").append(fajr).append("\n");
        sb.append("Sunrise: ").append(sunrise).append("\n");
        sb.append("Dhuhr: ").append(dhuhr).append("\n");
        sb.append("Asr: ").append(asr).append("\n");
        sb.append("Sunset: ").append(sunset).append("\n");
        sb.append("Maghrib: ").append(maghrib).append("\n");
        sb.append("Isha: ").append(isha).append("\n");
        sb.append("Imsak: ").append(imsak).append("\n");
        sb.append("Midnight: ").append(midnight).append("\n");
        sb.append("First Third: ").append(firstthird).append("\n");
        sb.append("Last Third: ").append(lastthird);
        return sb.toString();
    }
}
