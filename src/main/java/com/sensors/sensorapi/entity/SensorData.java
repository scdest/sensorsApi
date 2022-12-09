package com.sensors.sensorapi.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@Document
public class SensorData {
    private UUID id = UUID.randomUUID();

    private Double result;

    private Instant timestamp;

    private UUID sensorId;
}
