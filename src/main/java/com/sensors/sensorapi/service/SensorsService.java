package com.sensors.sensorapi.service;

import com.sensors.sensorapi.dto.SensorDataDto;
import com.sensors.sensorapi.dto.SensorRequest;
import com.sensors.sensorapi.entity.Sensor;
import com.sensors.sensorapi.entity.SensorData;
import com.sensors.sensorapi.repository.SensorDataRepository;
import com.sensors.sensorapi.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorsService {

    @Autowired
    private final SensorRepository sensorRepository;

    @Autowired
    private final SensorDataRepository sensorDataRepository;

    public UUID createSensor(SensorRequest request) {
        return sensorRepository.save(new Sensor(request.getMaxResults())).getId();
    }

    public UUID updateSensor(SensorRequest request, UUID id) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor not found by id " + id));
        sensor.setMaxResults(request.getMaxResults());
        sensorRepository.save(sensor);
        return sensor.getId();
    }

    public void deleteSensor(UUID id) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor not found by id " + id));
        sensorDataRepository.deleteAllBySensorId(id);
        sensorRepository.delete(sensor);
    }

    public void createSensorData(SensorDataDto dto, UUID sensorId) {
        if (!sensorRepository.existsById(sensorId)) {
            throw new RuntimeException("Sensor not found by id " + sensorId);
        }

        SensorData sensorData = new SensorData();
        sensorData.setSensorId(sensorId);
        sensorData.setResult(dto.getResult());
        sensorData.setTimestamp(dto.getTimestamp());

        sensorDataRepository.save(sensorData);
    }

    public List<SensorData> getSensorData(UUID id) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor not found by id " + id));
        PageRequest request = PageRequest.of(0, sensor.getMaxResults(), Sort.by(Sort.Direction.DESC, "timestamp"));
        return sensorDataRepository.findAllBySensorId(id, request).stream().toList();
    }

    public List<SensorData> getSensorDataForCleanup(UUID id) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor not found by id " + id));
        PageRequest request = PageRequest.of(1, sensor.getMaxResults(), Sort.by(Sort.Direction.DESC, "timestamp"));
        return sensorDataRepository.findAllBySensorId(id, request).stream().toList();
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public void deleteData(Collection<SensorData> data) {
        sensorDataRepository.deleteAll(data);
    }
}
