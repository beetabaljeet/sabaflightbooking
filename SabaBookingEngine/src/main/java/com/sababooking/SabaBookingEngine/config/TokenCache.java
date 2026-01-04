package com.sababooking.SabaBookingEngine.config;

import org.springframework.stereotype.Component;

@Component
public class TokenCache {
    private volatile String token;
    private volatile long expiresAtSec;

    public boolean isValid() {
        return token != null && (System.currentTimeMillis()/1000) < (expiresAtSec - 30);
    }
    public String get() { return token; }
    public void set(String t, int expiresIn) {
        token = t;
        expiresAtSec = (System.currentTimeMillis()/1000) + expiresIn;
    }
}
