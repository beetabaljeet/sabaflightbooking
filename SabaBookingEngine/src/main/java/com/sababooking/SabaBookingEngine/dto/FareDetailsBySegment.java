
package com.sababooking.SabaBookingEngine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FareDetailsBySegment {
    private String segmentId;
    private String cabin;
    private String fareBasis;
    @JsonProperty("class")
    private String bookingClass;
    private CheckedBags includedCheckedBags;
}
