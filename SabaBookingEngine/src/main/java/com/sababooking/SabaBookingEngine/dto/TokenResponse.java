
// src/main/java/com/example/amadeus/dto/TokenResponse.java
package com.sababooking.SabaBookingEngine.dto;

public class TokenResponse {
    public String access_token;
    public String token_type;
    public Integer expires_in;
    public String scope; // sometimes present

    @Override
    public String toString() {
        return "TokenResponse{access_token='" + access_token + "', token_type='" + token_type +
                "', expires_in=" + expires_in + ", scope='" + scope + "'}";
    }
}
