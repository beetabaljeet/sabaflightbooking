
package com.sababooking.SabaBookingEngine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fee {
    private String type;
    private String amount;
}
