package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private static List<Company> companyList = new ArrayList<>();
    private static List<Employees> employeesList = new ArrayList<>();


    @GetMapping()
    public List<Company> getAllCompany() {
        employeesList.add(new Employees(10, "Angelo", 23, "male", 1000));
        companyList.add(new Company(1, "alibaba" ,employeesList));

        return companyList;
    }
}
