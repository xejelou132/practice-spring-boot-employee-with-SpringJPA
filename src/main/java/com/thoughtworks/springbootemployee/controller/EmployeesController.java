package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Employees getEmployeeById(@PathVariable Integer employeeId) {
        employeesList.add(new Employees(1,"Angelo", 23,"male",1000));
        employeesList.add(new Employees(2,"Angela", 26,"female",900));

        return employeesList.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping(params = {"gender"})
    public List<Employees> getEmployeesByGender(@RequestParam("gender") String givenGender) {
        employeesList.add(new Employees(1,"Angelo", 23,"male",1000));
        employeesList.add(new Employees(2,"Angela", 26,"fe",900));

        return employeesList.stream()
                .filter(employee -> employee.getGender().equals(givenGender))
                .collect(Collectors.toList());
    }
//"?gender={givenGender}"

}
