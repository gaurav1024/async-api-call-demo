package com.example.demo.dto;

public record QuoteDTO(Long id, String quote, String author) {
    public static QuoteDTO DEFAULT() {
        return new QuoteDTO(0L, "Stay positive, work hard, make it happen.", "Anonymous");
    }
}
