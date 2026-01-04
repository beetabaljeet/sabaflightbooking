
// src/main/java/com/example/amadeus/controller/AmadeusAuthController.java
package com.sababooking.SabaBookingEngine.rest;


import com.sababooking.SabaBookingEngine.dto.TokenResponse;
import com.sababooking.SabaBookingEngine.service.AmadeusAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/amadeus")
public class AmadeusAuthController {

    private final AmadeusAuthService authService;

    public AmadeusAuthController(AmadeusAuthService authService) {
        this.authService = authService;
    }

    /**
     * GET /api/amadeus/token
     * Returns an OAuth2 access token from Amadeus test environment.
     */
    @GetMapping("/token")
    public Mono<ResponseEntity<TokenResponse>> getToken() {
        return authService.getAccessToken()
                .map(ResponseEntity::ok)
                .onErrorResume(ex -> Mono.just(ResponseEntity
                        .status(502) // Bad Gateway (upstream failure)
                        .body(errorToken(ex))));
    }


    private TokenResponse errorToken(Throwable ex) {
        TokenResponse err = new TokenResponse();
        err.access_token = null;
        err.token_type = "error";
        err.expires_in = 0;
        err.scope = ex.getMessage();
        return err;
    }
}
