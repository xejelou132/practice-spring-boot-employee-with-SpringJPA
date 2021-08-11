package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private List<Employees> employeesList = new ArrayList<>();
    @GetMapping
    public List<Employees> getAllEmployees() {
        employeesList.add(new Employees(1,"Angelo", 23,"male",1000));
        employeesList.add(new Employees(2,"Angela", 26,"female",900));

        return employeesList;
    }

    @GetMapping("/{employeeId}")
    public Employees getAllEmployees(@PathVariable Integer employeeId) {
        employeesList.add(new Employees(1,"Angelo", 23,"male",1000));
        employeesList.add(new Employees(2,"Angela", 26,"female",900));

        return employeesList.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }


}
