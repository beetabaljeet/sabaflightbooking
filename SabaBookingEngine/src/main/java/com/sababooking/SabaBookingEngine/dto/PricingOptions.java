
package com.sababooking.SabaBookingEngine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricingOptions {
    private List<String> fareType;
    private boolean includedCheckedBagsOnly;
}
