package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Repository.CompanyRepo;
import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CompanyTest {
    CompanyRepo repository = Mockito.mock(CompanyRepo.class);
    List<Employees> employeesList = new ArrayList<>();

    @BeforeEach
    void SetUp(){


        employeesList.add(new Employees(1, "Angelo", 23, "male", 1000, 1));
        employeesList.add(new Employees(2, "Angela", 26, "female", 900, 1));
    }
    @Test
    void shoul_return_all_employess_when_getallemployees_given_all_employess() {
        //given
        List<Company> companyList = new ArrayList<>();

        companyList.add(new Company(1, "OOCL", employeesList));

        when(repository.getAllCompanies()).thenReturn(companyList);
        CompanyService service = new CompanyService(repository);

        //when
        List<Company> actualCompany = service.getCompanyList();

        assertEquals(actualCompany.size(), companyList.size());
        assertEquals(actualCompany, companyList);
    }


}
