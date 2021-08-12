package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.Repository.RetiringEmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService{
    private Employees employees;

    @Resource
    private EmployeesRepo employeesRepo;

    public EmployeeService(EmployeesRepo employeesRepo) {
        this.employeesRepo = employeesRepo;
    }

    public Employees addEmployee(Employees employee){
        return employeesRepo.save(employee);
    }

    public List<Employees> getEmployeesList(){
        return employeesRepo.findAll();
    }


    public Employees findByID(Integer employeeId) {
       return employeesRepo.findAll()
               .stream()
               .filter(employees1 -> employees1.getId().equals(employeeId))
               .findFirst()
               .orElse(null);
    }

    public List<Employees> getByPage(int index, int page) {
        return  employeesRepo.findAll()
                .stream()
                .skip((long) (index - 1) *page)
                .limit(page)
                .collect(Collectors.toList());
    }

    public List<Employees> findByGender(String gender) {
        return employeesRepo.findAll()
                .stream()
                .filter(employees1 -> employees1.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employees updateById(Integer employeeId, Employees employee) {
        employeesRepo.findAll()
                .stream()
                .filter(employee1 -> employee1.getId().equals(employeeId))
                .findFirst()
                .ifPresent(employees1 -> {
                    employeesRepo.deleteById(employeeId);
                    employeesRepo.save(employee);

                })
                ;
       return employee;

    }

    public void deleteById(Integer employeeId) {
        employeesRepo.deleteById(employeeId);
    }

}
