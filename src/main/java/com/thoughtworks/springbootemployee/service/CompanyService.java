package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Repository.CompanyRepo;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepo companyRepo;

    public CompanyService(CompanyRepo companyRepository) {
        this.companyRepo = companyRepository;
    }

    public List<Company> getCompanyList() {
        return companyRepo.getAllCompanies();
    }

    public Company add(Company company) {
        return companyRepo.add(company);
    }

    public Company findById(Integer id) {
        return companyRepo.findCompanyById(id);
    }

    public Company updateById(Integer id, Company company) {
        return companyRepo.updateCompanyById(id, company);
    }

    public void deleteById(Integer id) {
        companyRepo.deleteById(id);
    }

    public List<Employees> getEmployeesByCompanyId(int companyId) {
        return companyRepo.getEmployeesByCompanyId(companyId);
    }

    public List<Company> getByPage(Integer page, Integer pageSize) {
        return companyRepo.getByPage(page, pageSize);
    }
}
