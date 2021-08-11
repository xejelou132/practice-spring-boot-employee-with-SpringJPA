package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private static List<Company> companyList = new ArrayList<>();
    private static List<Employees> employeesList = new ArrayList<>();


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

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompaniesByPagination(@RequestParam int page, @RequestParam int pageSize) {


        return companyList.stream().skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addCompany(@RequestBody Company newCompany) {
        Company companyToBeAdded = new Company(companyList.size() + 1,
                newCompany.getCompanyName(),
                newCompany.getEmployeesList());
        companyList.add(companyToBeAdded);

    }

    @PutMapping(path = "/{companyID}")
    public Company updateCompany(@PathVariable Integer companyID, @RequestBody Company companyToBeUpdated) {
        companyList.stream().filter(company -> company.getCompanyNumber().equals(companyID))
                .findFirst().ifPresent(company -> {
            companyList.remove(company);
            companyToBeUpdated.setCompanyNumber(companyID);
            companyList.add(companyToBeUpdated);
        });
        return companyToBeUpdated;
    }

    @DeleteMapping(path = "/{companyId}")

    public String deleteCompany(@PathVariable Integer companyId) {
        companyList.stream()
                .filter(company -> company.getCompanyNumber().equals(companyId))
                .findFirst()
                .ifPresent(company -> {
                    companyList.remove(company);
                });
        return "Deleted company" + companyId;
    }


}
