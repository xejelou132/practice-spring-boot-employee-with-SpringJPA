package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepo {
    private List<Company> companies = new ArrayList<>();

    public List<Company> getAllCompanies() {
        return companies;
    }

    public Company add(Company company) {
        companies.add(company);
        return company;
    }

    public Company getCompanyById(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getCompanyNumber().equals(companyId))
                .findFirst().orElse(null);

    }

    public Company updateCompanyById(Integer id, Company updatedCompany) {
        companies.stream()
                .filter(company -> company.getCompanyNumber().equals(id)).
                findFirst().ifPresent(company -> {
            companies.remove(company);
            updatedCompany.setCompanyNumber(id);
            companies.add(updatedCompany);
        });
        return updatedCompany;

    }

    public String deleteById(Integer id) {
        companies.stream()
                .filter(company -> company.getCompanyNumber().equals(id))
                .findFirst()
                .ifPresent(companies::remove);
        return "Deleted company" + id;
    }


    public List<Employees> getEmployeesByCompanyId(int companyId) {
        EmployeesRepo employeeRepository = new EmployeesRepo();
        return employeeRepository.getAllEmployees().stream()
                .filter(employee -> employee.getId().equals(companyId))
                .collect(Collectors.toList());
    }

    public List<Company> getByPage(Integer page, Integer pageSize) {
        return companies.stream()
                .skip((pageSize - 1) * page)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
