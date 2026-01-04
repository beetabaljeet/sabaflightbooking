
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalService {

    @JsonProperty("type")
    private String type;

    @JsonProperty("amount")
    private String amount;
}
