package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;
    private final List<Employees> employeesList = new ArrayList<>();

    @GetMapping()
    public List<Employees> getAllEmployees() {
        return employeesList;
    }

    @GetMapping("/{employeeId}")
    public Employees getEmployeeById(@PathVariable Integer employeeId) {
        return employeeService.findByID(employeeId);
    }

    @GetMapping(params = {"gender"})
    public List<Employees> getEmployeesByGender(@RequestParam("gender") String givenGender) {
        return employeeService.findByGender(givenGender);
    }

    @GetMapping(params = {"index", "size"})
    public List<Employees> getEmployeesByPagination(@RequestParam int index, @RequestParam int size) {
        return employeeService.getByPage(index, size);
    }

    @PostMapping
    public Employees addEmployee(@RequestBody Employees employee) {
        employee.setId(employeesList.size() + 1);
        return employeeService.addEmployee(employee);
    }


    @PutMapping(path = "/{employeeId}")
    public Employees updateEmployee(@PathVariable Integer employeeId, @RequestBody Employees employeesToBeUpdated) {
        return employeeService.updateById(employeeId, employeesToBeUpdated);
    }

    @DeleteMapping(path = "/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteById(employeeId);
        return "Deleted Employee " + employeeId;
    }
}
