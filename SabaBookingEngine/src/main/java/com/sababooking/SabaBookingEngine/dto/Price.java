
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("total")
    private String total;

    @JsonProperty("base")
    private String base;

    @JsonProperty("fees")
    private List<Fee> fees;

    @JsonProperty("grandTotal")
    private String grandTotal;
}
