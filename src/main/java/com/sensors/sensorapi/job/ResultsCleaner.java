package com.sensors.sensorapi.job;

import com.sensors.sensorapi.entity.Sensor;
import com.sensors.sensorapi.service.SensorsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResultsCleaner {

    @Autowired
    private final SensorsService service;

    @Scheduled(fixedDelay = 1000)
    public void cleanResults() {
        log.info("Cleaning started");
        List<Sensor> sensors = service.getAllSensors();
        for(Sensor sensor : sensors) {
            service.deleteData(service.getSensorDataForCleanup(sensor.getId()));
        }
    }
}
