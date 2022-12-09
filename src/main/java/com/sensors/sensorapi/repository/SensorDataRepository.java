package com.sensors.sensorapi.repository;

import com.sensors.sensorapi.entity.SensorData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SensorDataRepository extends MongoRepository<SensorData, UUID> {
    Page<SensorData> findAllBySensorId(UUID id, Pageable pageable);

    List<SensorData> deleteAllBySensorId(UUID id);
}
