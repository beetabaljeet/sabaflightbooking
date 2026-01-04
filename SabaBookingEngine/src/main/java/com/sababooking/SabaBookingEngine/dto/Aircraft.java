
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft {

    @JsonProperty("code")
    private String code;
}
