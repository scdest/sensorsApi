package com.sensors.sensorapi.repository;

import com.sensors.sensorapi.entity.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SensorRepository extends MongoRepository<Sensor, UUID> {
}
