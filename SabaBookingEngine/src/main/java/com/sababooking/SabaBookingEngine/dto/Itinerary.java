
package com.sababooking.SabaBookingEngine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("segments")
    private List<Segment> segments;
}
