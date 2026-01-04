
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelerPricing {

    @JsonProperty("travelerId")
    private String travelerId;

    @JsonProperty("fareOption")
    private String fareOption;

    @JsonProperty("travelerType")
    private String travelerType;

    @JsonProperty("price")
    private Price price;

    @JsonProperty("fareDetailsBySegment")
    private List<FareDetailsBySegment> fareDetailsBySegment;
}
