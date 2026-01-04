
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Operating {

    @JsonProperty("carrierCode")
    private String carrierCode;
}
