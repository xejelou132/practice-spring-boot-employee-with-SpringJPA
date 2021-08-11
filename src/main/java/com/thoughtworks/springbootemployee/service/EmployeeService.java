package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeesRepo employeesRepo;

    public EmployeeService(EmployeesRepo employeesRepo) {
        this.employeesRepo = employeesRepo;
    }

    public List<Employees> geteEmployeesList(){
        return employeesRepo.getAllEmployees();
    }
}
