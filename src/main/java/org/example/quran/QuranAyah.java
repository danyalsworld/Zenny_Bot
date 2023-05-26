package org.example.quran;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuranAyah {
    private int code;
    private String status;
    private AyahData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AyahData getData() {
        return data;
    }

    public void setData(AyahData data) {
        this.data = data;
    }
// Getters and Setters for code, status, and data

    // Inner class to represent the "data" field
    static class AyahData {
        private int number;
        private String text;

        // Getters and Setters for number and text

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}