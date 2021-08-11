package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Repository.CompanyRepo;
import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CompanyTest {
    CompanyRepo repository = Mockito.mock(CompanyRepo.class);
    List<Employees> employeesList = new ArrayList<>();
    List<Company> companyList = new ArrayList<>();

    @BeforeEach
    void SetUp() {

        employeesList.add(new Employees(1, "Angelo", 23, "male", 1000, 1));
        employeesList.add(new Employees(2, "Angela", 26, "female", 900, 1));
    }

    @Test
    void should_get_all_when_get_company() {
        //given
        List<Company> expectedCompany = asList(new Company(1, "OOCL", employeesList),
                new Company(2, "COSCO", employeesList));
        when(repository.getAllCompanies()).thenReturn(expectedCompany);
        CompanyService service = new CompanyService(repository);

        //when
        List<Company> actual = service.getCompanyList();

        //given
        assertEquals(2, actual.size());
    }

    @Test
    void should_create_company_when_create_given_company() {
        //given

        Company company = new Company(1, "OOCL", employeesList);
        CompanyService service = new CompanyService(repository);
        when(repository.add(company)).thenReturn(company);
        //when
        Company actual = service.add(company);
        //then
        assertEquals("OOCL", actual.getCompanyName());
    }

    @Test
    void should_get_company_when_get_by_name_given_company_id() {
        //given
        Company company = new Company(1, "OOCL", employeesList);
        when(repository.getCompanyById(company.getCompanyNumber())).thenReturn(company);
        CompanyService service = new CompanyService(repository);
        //when
        Company actual = service.findById(company.getCompanyNumber());
        //then
        assertEquals(company.getCompanyNumber(), actual.getCompanyNumber());
    }

    @Test
    void should_get_list_of_employee_when_search_given_certain_company() {
        //given
        Company company = new Company(1, "OOCL", employeesList);
        Employees employee = new Employees(1, "Tom", 18, "Male", 1000, 1);

        when(repository.getEmployeesByCompanyId(company.getCompanyNumber())).thenReturn(Collections.singletonList(employee));
        CompanyService companyService = new CompanyService(repository);
        //when
        List<Employees> actual = companyService.getEmployeesByCompanyId(company.getCompanyNumber());
        //then
        assertTrue(actual.contains(employee));
    }

    @Test
    void should_update_company_when_update_by_company_id_given_company_id() {
        //given
        Company company = new Company(1, "OOCL", employeesList);
        Company updateCompany = new Company(1, "COSCO", employeesList);

        when(repository.updateCompanyById(company.getCompanyNumber(), updateCompany)).thenReturn(updateCompany);
        CompanyService service = new CompanyService(repository);
        //when
        Company actual = service.updateById(company.getCompanyNumber(), updateCompany);
        //then
        assertEquals("COSCO", actual.getCompanyName());
    }

    @Test
    void should_delete_company_when_delete_by_company_id_given_company() {
        //given
        Company company = new Company(1, "OOCL", employeesList);
        CompanyService service = new CompanyService(repository);
        //when
        service.deleteById(company.getCompanyNumber());
        //then
        assertNull(repository.deleteById(company.getCompanyNumber()));
    }

    @Test
    void should_return_2_company_when_getByPage_given_company_request() {
        //given
        List<Company> companies =
                asList(new Company(1, "OOCL", employeesList),
                        new Company(2, "COSCO", employeesList),
                        new Company(3, "COSCON", employeesList),
                        new Company(4, "PANASIA", employeesList),
                        new Company(5, "OOL", employeesList));
        EmployeesRepo employeeRepository = Mockito.mock(EmployeesRepo.class);
        when(repository.getByPage(1, 5)).thenReturn(companies);
        CompanyService companyService = new CompanyService(repository);
        //WHEN
        List<Company> companyActual = companyService.getByPage(1, 5);
        //THEN
        assertEquals(5, companyActual.size());
        //TODO change to smaller and return the data.
    }

}
