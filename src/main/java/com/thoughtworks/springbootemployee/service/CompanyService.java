package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Repository.CompanyRepo;
//import com.thoughtworks.springbootemployee.Repository.RetiringCompanyRepo;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepo companyRepo;

    public CompanyService(CompanyRepo CompanyRepository) {
        this.companyRepo = CompanyRepository;
    }

    public List<Company> getCompanyList() {
        return companyRepo.findAll();
    }

    public Company add(Company company) {
        return companyRepo.save(company);
    }

    public Company findById(Integer id) {
        return companyRepo.findAll()
                .stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public Company updateById(Integer id, Company company) {
        Company editedCompany = this.findById(id);
        if(company != null){
            editedCompany.setId(id);
            editedCompany.setCompanyName(company.getCompanyName());
            return companyRepo.save(editedCompany);
        }
        return editedCompany;
    }

    public void deleteById(Integer id) {
        companyRepo.deleteById(id);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        return companyRepo.findById(companyId).get().getEmployeesList();
    }

    public List<Company> getByPage(Integer page, Integer pageSize) {
        return companyRepo.findAll(PageRequest.of(page,pageSize)).toList();
    }
}
