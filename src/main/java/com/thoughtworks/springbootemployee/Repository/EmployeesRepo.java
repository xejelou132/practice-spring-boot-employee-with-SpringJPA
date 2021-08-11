package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    public List<Employees> getByPage(int index, int page) {
        return employees.stream().skip((long) (index - 1) * page)
                .limit(page)
                .collect(Collectors.toList());
    }

    public List<Employees> getGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employees updateById(Integer employeeId, Employees updateEmployee) {
          employees.stream().
                filter(employees1 -> employees1.getId().equals(employeeId))
                .findFirst()
                .ifPresent(employees1 -> {
                   employees.remove(employees1);
                    updateEmployee.setId(employeeId);
                    employees.add(updateEmployee);
                });
         return  updateEmployee;
    }

    public Employees deleteById(Integer employeeId) {

        Employees employeesToRemove = employees.stream()
                .filter(employees -> employees.getId().equals(employeeId))
                .findFirst().get();
        employees.remove(employeesToRemove);

        return employeesToRemove;
    }
}
