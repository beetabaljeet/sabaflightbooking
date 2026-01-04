package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {

    @JsonProperty("timestamp")
    @Builder.Default
    private Instant timestamp = Instant.now();

    @JsonProperty("status")
    private int status; // HTTP status code (e.g., 400, 502)

    @JsonProperty("error")
    private String error; // HTTP reason phrase (e.g., "Bad Gateway")

    @JsonProperty("code")
    private String code;  // App-specific error code (optional, e.g., "AMADEUS_TOKEN_ERROR")

    @JsonProperty("message")
    private String message; // Human-readable message

    @JsonProperty("path")
    private String path; // Request URI where error occurred

    @JsonProperty("details")
    private List<String> details; // Optional list of details (validation errors, etc.)
}
