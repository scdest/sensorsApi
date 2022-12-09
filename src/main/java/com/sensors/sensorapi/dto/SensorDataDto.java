package com.sensors.sensorapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorDataDto {
    @NotNull
    @Positive
    private Double result;
    @NotNull
    private Instant timestamp;
}
