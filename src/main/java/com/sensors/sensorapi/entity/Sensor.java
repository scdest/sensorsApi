package com.sensors.sensorapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
@NoArgsConstructor
public class Sensor {
    @Id
    private UUID id = UUID.randomUUID();

    private Integer maxResults;

    public Sensor(Integer maxResults) {
        this.maxResults = maxResults;
    }
}
