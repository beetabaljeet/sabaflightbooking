package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightOffer {

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private String id;

    @JsonProperty("source")
    private String source;

    @JsonProperty("instantTicketingRequired")
    private boolean instantTicketingRequired;

    @JsonProperty("nonHomogeneous")
    private boolean nonHomogeneous;

    @JsonProperty("oneWay")
    private boolean oneWay;

    // For boolean fields with "is" prefix, explicitly bind the JSON name.
    @JsonProperty("isUpsellOffer")
    private boolean isUpsellOffer;

    @JsonProperty("lastTicketingDate")
    private String lastTicketingDate;

    // Accept both lastTicketingDateTime and lastTicketingDate for robustness
    @JsonProperty("lastTicketingDateTime")
    @JsonAlias({"lastTicketingDateTime"})
    private String lastTicketingDateTime;

    @JsonProperty("numberOfBookableSeats")
    private Integer numberOfBookableSeats;

    @JsonProperty("itineraries")
    private List<Itinerary> itineraries;

    @JsonProperty("price")
    private Price price;

    @JsonProperty("additionalServices")
    private List<AdditionalService> additionalServices;

    @JsonProperty("pricingOptions")
    private PricingOptions pricingOptions;

    @JsonProperty("validatingAirlineCodes")
    private List<String> validatingAirlineCodes;

    @JsonProperty("travelerPricings")
    private List<TravelerPricing> travelerPricings;
}
