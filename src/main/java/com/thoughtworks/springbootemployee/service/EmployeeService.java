package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFound;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
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

    @Resource
    private EmployeeMapper employeesMapper;

    private EmployeeService(){

    }

    public EmployeeService(EmployeesRepo employeesRepo) {
        this.employeesRepo = employeesRepo;
    }

    public Employee addEmployee(Employee employee){
        return employeesRepo.save(employee);
    }

    public List<EmployeeResponse> getEmployeesList(){
        return employeesRepo.findAll().stream()
                .map(employeesMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse findByID(Integer employeeId) {
        return employeesRepo.findAll()
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .map(employeesMapper::toResponse)
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFound("Employee Not Found"));
    }

    public List<EmployeeResponse> getByPage(int index, int page) {
        return  employeesRepo.findAll(PageRequest.of(index-1,page)).stream()
                .map(employeesMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<EmployeeResponse> findByGender(String gender) {
        return employeesRepo.findAll().stream()
                .filter(employees1 -> employees1.getGender().equals(gender))
                .map(employeesMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Employee updateById(Integer employeeId, Employee employeeUpdate) {

        Employee employee = employeesRepo.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFound("Employee Not Found"));

        validateEmployeeName(employeeUpdate.getName());

        employee.setName(employeeUpdate.getName());
        employee.setAge(employeeUpdate.getAge());
        employee.setGender(employeeUpdate.getGender());
        employee.setSalary(employeeUpdate.getSalary());

        return employeesRepo.save(employee);
    }

    private void validateEmployeeName(String employeeName) {
        if (employeeName == null) {
            throw new EmployeeNotFound("Employee Not Found");
        }
    }

    public void deleteById(Integer employeeId) {
        employeesRepo.deleteById(employeeId);
    }

}
