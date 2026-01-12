package com.rothur.UserDemo.util;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class UserActuator implements HealthIndicator {

    @Override
    public Health health() {
        boolean isServiceUp = checkUserSubsystem(); // Your logic here
        if (isServiceUp) {
            return Health.up().withDetail("User Service", "Running smoothly").build();
        }
        return Health.down().withDetail("User Service", "Down for maintenance").build();
    }

    private boolean checkUserSubsystem() {
        return true;
    }
}
