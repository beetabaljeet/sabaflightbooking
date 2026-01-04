
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FareDetailsBySegment {

    @JsonProperty("segmentId")
    private String segmentId;

    @JsonProperty("cabin")
    private String cabin;

    @JsonProperty("fareBasis")
    private String fareBasis;

    // JSON key is "class", which is a reserved word in Java, so we map it explicitly
    @JsonProperty("class")
    private String bookingClass;

    @JsonProperty("includedCheckedBags")
    private CheckedBags includedCheckedBags;
}

