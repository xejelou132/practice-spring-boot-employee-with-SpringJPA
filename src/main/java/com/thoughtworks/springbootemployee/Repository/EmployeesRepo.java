package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class EmployeesRepo {

    private List<Employees> employees = new ArrayList<>();

    public List<Employees> getAllEmployees() {
        return employees;
    }

    public Employees add(Employees employee){
        employees.add(employee);
        return employee;
    }

    public Employees  getEmployeeById(Integer employeeId) {
        return employees.stream().
                filter(employee -> employee.getId().equals(employeeId))
                .findFirst().orElse(null);
    }
}
