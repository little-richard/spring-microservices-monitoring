package com.ricardomendesdev.example.apigateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/employeeDetails/{employeeId}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getStudents(@PathVariable int employeeId){
        System.out.println("Getting Employee details for " + employeeId);

        String response = restTemplate.exchange(
                "http://localhost:8011/findEmployeeDetails/{employeeId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>(){},
                employeeId).getBody();

        System.out.println("Response body " + response);

        return "Employee Id - " + employeeId + " [ Employee Details " + response + " ]";
    }

    public String fallbackMethod(int employeeId){
        return "Fallback response: No Employee details available temporarily";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
