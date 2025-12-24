
package com.sababooking.SabaBookingEngine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dictionaries {
    private Map<String, Location> locations;
    private Map<String, String> aircraft;
    private Map<String, String> currencies;
    private Map<String, String> carriers;
}
