package com.example.FirstRestApp.restTemplate;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class RestTemplateClass {

    private final static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        //System.out.println(newSensorRegistration());
        sendSensorMeasurement();
        //System.out.println(getAllMeasurements());
        createTemperatureGraph();
    }

    private static ResponseEntity<HttpStatus> newSensorRegistration() {
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", "third-sensor");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
        String url = "http://localhost:8080/sensors/registration";
        return restTemplate.postForEntity(url, request, HttpStatus.class);
    }

    private static void sendSensorMeasurement() {
        Sensor sensor = new Sensor("second_sensor");
        Random random = new Random();
        for (int i = 0; i < 100; i++) {

            Measurement measurement = new Measurement((Math.random() * 200 - 100),
                    random.nextBoolean(),
                    sensor);

            HttpEntity<Measurement> request = new HttpEntity<>(measurement);
            String url = "http://localhost:8080/measurements/add";
            System.out.println(restTemplate.postForEntity(url, request, HttpStatus.class));
        }
    }

    private static String getAllMeasurements() {
        String url = "http://localhost:8080/measurements";
        return restTemplate.getForObject(url, String.class);
    }

    private static void createTemperatureGraph() {
        String url = "http://localhost:8080/measurements";
        List<Map<String, Double>> measurementList = restTemplate.getForObject(url, List.class);

        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        int i = 1;
        for (Map<String, Double> measurement : measurementList) {
            xData.add((double) i++);
            yData.add(measurement.get("value"));
        }

        XYChart chart = QuickChart.getChart("Temperature chart", "#M", "t", "y(x)", xData, yData);
        new SwingWrapper<>(chart).displayChart();
    }

}
