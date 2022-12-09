package com.sensors.sensorapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SensorRequest {
    @Positive
    @NotNull
    private Integer maxResults;
}
