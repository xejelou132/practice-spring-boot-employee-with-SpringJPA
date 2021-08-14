package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFound;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService{

    @Resource
    private EmployeesRepo employeesRepo;

    private EmployeeService(){

    }

    public EmployeeService(EmployeesRepo employeesRepo) {
        this.employeesRepo = employeesRepo;
    }

    public Employee addEmployee(Employee employee){
        return employeesRepo.save(employee);
    }

    public List<Employee> getEmployeesList(){
        return employeesRepo.findAll();
    }


    public Employee findByID(Integer employeeId)
    {
        return employeesRepo.findById(employeeId).orElseThrow(() -> new EmployeeNotFound("Employee Not Found"));
    }

    public List<Employee> getByPage(int index, int page) {
        return  employeesRepo.findAll(PageRequest.of(index-1,page)).toList();
    }

    public List<Employee> findByGender(String gender) {
        return employeesRepo.findAll()
                .stream()
                .filter(employees1 -> employees1.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee updateById(Integer employeeId, Employee employee) {
        Employee editedEmployee = this.findByID(employeeId);
        if(employee != null){
            editedEmployee.setAge(employee.getAge());
            editedEmployee.setId(employeeId);
            editedEmployee.setCompanyId(employee.getCompanyId());
            editedEmployee.setName(employee.getName());
            editedEmployee.setSalary(employee.getSalary());
            editedEmployee.setGender(employee.getGender());
            employeesRepo.delete(employee);
            return employeesRepo.save(editedEmployee);
        }
        return editedEmployee;
    }

    public void deleteById(Integer employeeId) {
        employeesRepo.deleteById(employeeId);
    }

}
