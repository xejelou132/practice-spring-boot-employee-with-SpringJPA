package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private static List<Company> companyList = new ArrayList<>();
    private static List<Employees> employeesList = new ArrayList<>();
    private static List<Employees> employeesList2 = new ArrayList<>();

    static {
        employeesList.add(new Employees(10, "Angelo", 23, "male", 1000, 1));
        employeesList.add(new Employees(11, "Ktiz", 23, "male", 100, 1));
        companyList.add(new Company(1, "alibaba" ,employeesList));
        companyList.add(new Company(2, "alibaba2" ,employeesList2));
    }


    @GetMapping()
    public List<Company> getAllCompany() {
        return companyList;
    }

    @GetMapping("/{companieId}")
    public Company getCompanyById(@PathVariable Integer companieId) {
        return companyList.stream()
                .filter(company -> company.getCompanyNumber().equals(companieId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/{companieId}/employees")
    public List<Employees> getEmployeeByCompanyId(@PathVariable Integer companieId) {
        return employeesList.stream()
                .filter(company -> company.getCompanyId().equals(companieId))
                .collect(Collectors.toList());
    }

}
