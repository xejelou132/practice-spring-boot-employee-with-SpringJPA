package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private static List<Company> companyList = new ArrayList<>();
    private static List<Employee> employeeList = new ArrayList<>();

    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public List<Company> getAllCompany() {
        return companyService.getCompanyList();
    }

    @GetMapping("/{companieId}")
    public Company getCompanyById(@PathVariable Integer companieId) {
        return companyService.findById(companieId);
    }

    @GetMapping("/{companieId}/employees")
    public List<Employee> getEmployeeByCompanyId(@PathVariable Integer companieId) {
        return companyService.getEmployeesByCompanyId(companieId);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompaniesByPagination(@RequestParam int page, @RequestParam int pageSize) {
        return companyService.getByPage(page, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addCompany(@RequestBody Company newCompany) {
        companyService.add(newCompany);

    }

    @PutMapping(path = "/{companyID}")
    public Company updateCompany(@PathVariable Integer companyID, @RequestBody Company companyToBeUpdated) {
        return companyService.updateById(companyID, companyToBeUpdated);
    }

    @DeleteMapping(path = "/{companyId}")

    public String deleteCompany(@PathVariable Integer companyId) {
        companyService.deleteById(companyId);
        return "Deleted company" + companyId;
    }


}
