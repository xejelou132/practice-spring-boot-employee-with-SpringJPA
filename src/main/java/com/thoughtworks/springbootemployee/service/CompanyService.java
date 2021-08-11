package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Repository.CompanyRepo;
import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyService {

    @Resource
    private CompanyRepo companyRepo;

    public CompanyService(CompanyRepo companiesRepo) {
        this.companyRepo = companiesRepo;
    }

    public List<Company> getCompanyList(){
        return companyRepo.getAllCompanies();
    }

    public Company create(Company company) {
        return companyRepo.add(company);
    }
}
