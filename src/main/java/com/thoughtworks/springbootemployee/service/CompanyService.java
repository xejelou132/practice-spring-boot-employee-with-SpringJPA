package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Repository.CompanyRepo;
//import com.thoughtworks.springbootemployee.Repository.RetiringCompanyRepo;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.exception.CompanyNotFound;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private CompanyRepo companyRepo;

    private CompanyMapper companyMapper;

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
        return companyRepo.findById(id).orElseThrow(()->new CompanyNotFound("Company Not Found"));
    }


    public Company updateById(Integer id, Company updatedCompany) {

        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new CompanyNotFound("Company Not Found"));

        validateCompany(updatedCompany.getCompanyName());

        company.setCompanyName(updatedCompany.getCompanyName());
        return companyRepo.save(company);
    }

    private void validateCompany(String companyName) {
        if (companyName == null) {
            throw new CompanyNotFound("Company Not Found");
        }
    }
    public void deleteById(Integer id) {
        companyRepo.deleteById(id);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        return companyRepo.findById(companyId).get().getEmployeesList();
    }

    public List<Company> getByPage(Integer page, Integer pageSize) {
        return companyRepo.findAll(PageRequest.of(page-1,pageSize)).toList();
    }
}
