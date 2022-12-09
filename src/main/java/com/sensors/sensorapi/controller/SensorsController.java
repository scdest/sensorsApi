package com.sensors.sensorapi.controller;

import com.sensors.sensorapi.dto.SensorRequest;
import com.sensors.sensorapi.dto.SensorResponse;
import com.sensors.sensorapi.dto.SensorDataDto;
import com.sensors.sensorapi.service.SensorsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sensors")
@RequiredArgsConstructor
public class SensorsController {

    private final SensorsService service;

    @PostMapping
    public SensorResponse createSensor(@RequestBody @Valid SensorRequest request) {
        return new SensorResponse(
                service.createSensor(request)
        );
    }

    @PutMapping("/{id}")
    public SensorResponse updateSensor(@RequestBody @Valid SensorRequest request, @PathVariable UUID id) {
        return new SensorResponse(
                service.updateSensor(request, id)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable UUID id) {
        service.deleteSensor(id);
    }

    @PostMapping("/{id}/data")
    public void createSensorData(@RequestBody @Valid SensorDataDto dto, @PathVariable UUID id) {
        service.createSensorData(dto, id);
    }

    @GetMapping("/{id}/data")
    public List<SensorDataDto> getSensorData(@PathVariable UUID id) {
        return service.getSensorData(id)
                .stream()
                .map(s -> new SensorDataDto(s.getResult(), s.getTimestamp()))
                .toList();
    }
}
