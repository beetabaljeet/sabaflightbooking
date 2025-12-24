package com.sababooking.SabaBookingEngine.service;

import com.sababooking.SabaBookingEngine.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import com.sababooking.SabaBookingEngine.properties.AmadeusProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AmadeusAuthService {

    private final WebClient amadeusWebClient;
    private final AmadeusProperties props;

    public AmadeusAuthService(@Qualifier("amadeusWebClient") WebClient amadeusWebClient, AmadeusProperties props) {
        this.amadeusWebClient = amadeusWebClient;
        this.props = props;
    }

    public Mono<TokenResponse> getAccessToken() {
        return amadeusWebClient.post()
                .uri("/v1/security/oauth2/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("client_id", props.getClientId())
                        .with("client_secret", props.getClientSecret()))
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .defaultIfEmpty("")
                                .flatMap(body -> Mono.error(new RuntimeException(
                                        "Amadeus token request failed: HTTP " +
                                                clientResponse.statusCode().value() + " - " + body))))
                .bodyToMono(TokenResponse.class);
    }
}
