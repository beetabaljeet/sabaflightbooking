
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Segment {

    @JsonProperty("departure")
    private Endpoint departure;

    @JsonProperty("arrival")
    private Endpoint arrival;

    @JsonProperty("carrierCode")
    private String carrierCode;

    @JsonProperty("number")
    private String number;

    @JsonProperty("aircraft")
    private Aircraft aircraft;

    @JsonProperty("operating")
    private Operating operating;

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("id")
    private String id;

    @JsonProperty("numberOfStops")
    private Integer numberOfStops;

    @JsonProperty("blacklistedInEU")
    private boolean blacklistedInEU;
}
