
package com.sababooking.SabaBookingEngine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightOfferSearchResponse {
    private Meta meta;
    private List<FlightOffer> data;
    private Dictionaries dictionaries;
}
