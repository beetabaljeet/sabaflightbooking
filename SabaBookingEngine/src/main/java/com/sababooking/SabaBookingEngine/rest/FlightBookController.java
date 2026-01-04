
// src/main/java/com/sababooking/SabaBookingEngine/rest/FlightBookController.java
package com.sababooking.SabaBookingEngine.rest;

import com.sababooking.SabaBookingEngine.dto.TokenResponse;
import com.sababooking.SabaBookingEngine.dto.FlightOfferSearchResponse;
import com.sababooking.SabaBookingEngine.service.FlightOfferService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/amadeus")
@Validated
public class FlightBookController {

    private final FlightOfferService flightOfferService;

    public FlightBookController(FlightOfferService flightOfferService) {
        this.flightOfferService = flightOfferService;
    }



    @GetMapping("/flight-offers")
    public Mono<ResponseEntity<FlightOfferSearchResponse>> flightOffers(
            @RequestParam @NotBlank String originLocationCode,
            @RequestParam @NotBlank String destinationLocationCode,
            @RequestParam @NotBlank String departureDate, // YYYY-MM-DD
            @RequestParam(required = false) String returnDate, // YYYY-MM-DD (optional for one-way)
            @RequestParam(defaultValue = "1") @Min(1) int adults,
            @RequestParam(defaultValue = "5") @Min(1) int max
    ) {
        return flightOfferService.searchFlightOffers(
                        originLocationCode,
                        destinationLocationCode,
                        departureDate,
                        returnDate,
                        adults,
                        max)
                .map(ResponseEntity::ok)
                .onErrorResume(ex -> Mono.just(ResponseEntity
                        .status(502)
                        .body(errorResponse(ex))));
    }

    // --- Helpers for error bodies (simple placeholders) ---

    private TokenResponse errorToken(Throwable ex) {
        // Minimal error payload mirroring TokenResponse shape
        return new TokenResponse();
    }

    private FlightOfferSearchResponse errorResponse(Throwable ex) {
        // You can put error info in meta; data stays null
        return new FlightOfferSearchResponse();
    }

    private String safeMessage(Throwable ex) {
        return ex == null ? "Unknown error" : String.valueOf(ex.getMessage());
    }
}
