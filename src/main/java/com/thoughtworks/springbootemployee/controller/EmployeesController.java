package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final List<Employees> employeesList = new ArrayList<>();
    private EmployeeService employeeService = new EmployeeService(new EmployeesRepo());

    @GetMapping()
    public List<Employees> getAllEmployees() {
        return employeesList;
    }

    @GetMapping("/{employeeId}")
    public Employees getEmployeeById(@PathVariable Integer employeeId) {
        return employeesList.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping(params = {"gender"})
    public List<Employees> getEmployeesByGender(@RequestParam("gender") String givenGender) {
        return employeesList.stream()
                .filter(employee -> employee.getGender().equals(givenGender))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"index", "size"})
    public List<Employees> getEmployeesByPagination(@RequestParam int index, @RequestParam int size) {
        return employeesList.stream().skip((long) (index - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Employees addEmployee(@RequestBody Employees employee) {
        employee.setId(employeesList.size() + 1);
        employeesList.add(employee);
        return employee;
    }


    @PutMapping(path = "/{employeeId}")
    public Employees updateEmployee(@PathVariable Integer employeeId, @RequestBody Employees employeesToBeUpdated) {
        employeesList.stream().
                filter(employees1 -> employees1.getId().equals(employeeId))
                .findFirst()
                .ifPresent(employees -> {
                    employeesList.remove(employees);
                    employeesToBeUpdated.setId(employeeId);
                    employeesList.add(employeesToBeUpdated);
                });

        return employeesToBeUpdated;
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        Employees employeesToRemove = employeesList.stream()
                .filter(employees -> employees.getId().equals(employeeId))
                .findFirst().get();

        employeesList.remove(employeesToRemove);
    }
}
