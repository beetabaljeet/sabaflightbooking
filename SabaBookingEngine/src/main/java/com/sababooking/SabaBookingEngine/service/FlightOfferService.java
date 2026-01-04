package com.sababooking.SabaBookingEngine.service;

import com.sababooking.SabaBookingEngine.config.TokenCache;
import com.sababooking.SabaBookingEngine.dto.FlightOfferSearchResponse;
import com.sababooking.SabaBookingEngine.dto.TokenResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FlightOfferService {

    private final WebClient webClient;
    private final AmadeusAuthService authService;
    private final TokenCache tokenCache;
    public FlightOfferService(WebClient amadeusWebClient, AmadeusAuthService authService, TokenCache tokenCache) {
        this.webClient = amadeusWebClient;
        this.authService = authService;
        this.tokenCache = tokenCache;
    }



    private Mono<String> getValidToken() {
        // Use defer to avoid evaluating cache validity at assembly time
        return Mono.defer(() -> {
            if (tokenCache.isValid()) {
                return Mono.just(tokenCache.get());
            }
            return authService.getAccessToken()
                    .map(t -> {
                        String accessToken = resolveAccessToken(t);
                        int expiresIn = resolveExpiresIn(t);
                        tokenCache.set(accessToken, expiresIn);
                        return accessToken;
                    });
        });
    }



// --- Helpers ---

    private String resolveAccessToken(TokenResponse t) {
        // Adjust based on your TokenResponse fields:
        // If using camelCase private field + getter:
        // return t.getAccessToken();
        // If using public snake_case field:
        return t.access_token;
    }

    private int resolveExpiresIn(TokenResponse t) {
        // If using camelCase private field + getter:
        // return t.getExpiresIn();
        return t.expires_in;
    }

    public Mono<FlightOfferSearchResponse> searchFlightOffers(
            String originLocationCode,
            String destinationLocationCode,
            String departureDate,  // YYYY-MM-DD
            String returnDate,     // YYYY-MM-DD
            int adults,
            int max
    ) {
        return getValidToken()
                .switchIfEmpty(Mono.error(new IllegalStateException("No access token from getValidToken()")))
                .flatMap(token -> webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/v2/shopping/flight-offers")
                                .queryParam("originLocationCode", originLocationCode)
                                .queryParam("destinationLocationCode", destinationLocationCode)
                                .queryParam("departureDate", departureDate)
                                .queryParam("returnDate", returnDate)
                                .queryParam("adults", adults)
                                .queryParam("max", max)
                                .build())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)   // <-- FIXED SPACE
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        // Handle 4xx explicitly (401/403/404/422 etc.)
                        .onStatus(HttpStatusCode::is4xxClientError, resp ->
                                resp.bodyToMono(String.class)
                                        .defaultIfEmpty("")
                                        .flatMap(body -> {
                                            System.err.println("Amadeus 4xx: " + resp.statusCode().value() + " - " + body);
                                            return Mono.error(new RuntimeException(
                                                    "Flight offers request failed (client): HTTP " +
                                                            resp.statusCode().value() + " - " + body));
                                        }))
                        // Handle 5xx errors
                        .onStatus(HttpStatusCode::is5xxServerError, resp ->
                                resp.bodyToMono(String.class)
                                        .defaultIfEmpty("")
                                        .flatMap(body -> {
                                            System.err.println("Amadeus 5xx: " + resp.statusCode().value() + " - " + body);
                                            return Mono.error(new RuntimeException(
                                                    "Amadeus server error: HTTP " +
                                                            resp.statusCode().value() + " - " + body));
                                        }))
                        .bodyToMono(FlightOfferSearchResponse.class)
                );
    }


}
