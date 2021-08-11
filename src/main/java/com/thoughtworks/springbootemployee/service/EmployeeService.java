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

    public List<Employees> getByPage(int index, int page) {
        return  employeesRepo.getByPage(index , page);
    }

    public List<Employees> findByGender(String gender) {
        return employeesRepo.getGender(gender);
    }

    public Employees updateById(Integer employeeId, Employees employee) {
        return employeesRepo.updateById(employeeId , employee);
    }

    public String deleteById(Integer employeeId) {
        return employeesRepo.deleteById(employeeId);
    }
}
