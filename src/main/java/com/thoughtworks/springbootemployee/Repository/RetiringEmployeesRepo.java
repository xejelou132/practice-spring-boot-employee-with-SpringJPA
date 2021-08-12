package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class RetiringEmployeesRepo {

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee add(Employee employee){
        employees.add(employee);
        return employee;
    }

    public Employee getEmployeeById(Integer employeeId) {
        return employees.stream().
                filter(employee -> employee.getId().equals(employeeId))
                .findFirst().orElse(null);
    }

    public List<Employee> getByPage(int index, int page) {
        return employees.stream().skip((long) (index - 1) * page)
                .limit(page)
                .collect(Collectors.toList());
    }

    public List<Employee> getGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee updateById(Integer employeeId, Employee updateEmployee) {
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

    public String deleteById(Integer employeeId) {
           employees.stream()
                .filter(employees -> employees.getId().equals(employeeId))
                .findFirst()
                .ifPresent(employees::remove);

        return  "Deleted Employee " + employeeId;
    }
}
