
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endpoint {

    @JsonProperty("iataCode")
    private String iataCode;

    @JsonProperty("at")
    private String at;
}
