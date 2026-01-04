
package com.sababooking.SabaBookingEngine.dto;




import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dictionaries {

        @JsonProperty("locations")
        private Map<String, Location> locations;

        @JsonProperty("aircraft")
        private Map<String, String> aircraft;

        @JsonProperty("currencies")
        private Map<String, String> currencies;

        @JsonProperty("carriers")
        private Map<String, String> carriers;
}
