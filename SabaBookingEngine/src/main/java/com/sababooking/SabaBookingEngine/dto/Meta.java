
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Meta {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("links")
    private Links links;
}
