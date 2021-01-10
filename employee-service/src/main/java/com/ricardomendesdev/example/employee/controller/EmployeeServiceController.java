package com.ricardomendesdev.example.employee.controller;

import com.ricardomendesdev.example.employee.beans.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class EmployeeServiceController {

    private static final Map<Integer, Employee> employeeData = new HashMap<Integer, Employee>() {

        private static final long serialVersionUID = -3970206781360313502L;

        {
            put(111, new Employee("Employee 1", 111));
            put(222, new Employee("Employee 2", 222));
        }

    };

    @GetMapping(value = "/findEmployeeDetails/{employeeId}")
    public Employee getEmployeeDetails(@PathVariable int employeeId) {
        System.out.println("Getting Employee details for " + employeeId);

        Employee employee = employeeData.get(employeeId);

        if(Objects.isNull(employee)){
            employee = new Employee("Employee 3", 333);
        }

        return employee;
    }
}
