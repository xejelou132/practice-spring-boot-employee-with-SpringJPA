package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeService {

    @Resource
    private EmployeesRepo employeesRepo;

    public EmployeeService(EmployeesRepo employeesRepo) {
        this.employeesRepo = employeesRepo;
    }

    public List<Employees> getEmployeesList(){
        return employeesRepo.getAllEmployees();
    }

    public Employees create(Employees employee) {
        return employeesRepo.add(employee);
    }

    public Employees findByID(Integer employeeId) {
       return employeesRepo.getEmployeeById(employeeId);
    }
}
