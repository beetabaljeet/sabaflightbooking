
package com.sababooking.SabaBookingEngine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightOffer {
    private String type;
    private String id;
    private String source;
    private boolean instantTicketingRequired;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private boolean isUpsellOffer;
    private String lastTicketingDate;
    @JsonAlias({"lastTicketingDateTime"})
    private String lastTicketingDateTime;
    private Integer numberOfBookableSeats;
    private List<Itinerary> itineraries;
    private Price price;
    private List<AdditionalService> additionalServices;
    private PricingOptions pricingOptions;
    private List<String> validatingAirlineCodes;
    private List<TravelerPricing> travelerPricings;
}
