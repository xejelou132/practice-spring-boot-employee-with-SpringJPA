package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
}
