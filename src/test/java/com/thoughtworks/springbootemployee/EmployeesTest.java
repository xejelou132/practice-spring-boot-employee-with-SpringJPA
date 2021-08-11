package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeesTest {

    EmployeesRepo repository = Mockito.mock(EmployeesRepo.class);

    @Test
    void shoul_return_all_employess_when_getallemployees_given_all_employess() {
        //given
        List<Employees> employeesList = new ArrayList<>();

        employeesList.add(new Employees(1, "Angelo", 23, "male", 1000, 1));
        employeesList.add(new Employees(2, "Angela", 26, "female", 900, 1));

        when(repository.getAllEmployees()).thenReturn(employeesList);
        EmployeeService service = new EmployeeService(repository);

        //when
        List<Employees> actualEmployess = service.getEmployeesList();

        assertEquals(actualEmployess.size(), employeesList.size());
        assertEquals(actualEmployess, employeesList);
    }


    @Test
    void shoul_return_1_employess_when_add_employee_given_1_employee() {
        //given
        Employees employee = new Employees(1, "Leo", 18, "male", 1000, 1);
        EmployeeService service = new EmployeeService(repository);
        when(repository.add(employee)).thenReturn(employee);
        //when
        Employees actual = service.create(employee);
        //then
        assertEquals(1, actual.getId());
    }

    @Test
    void should_get_employee_when_get_by_id_given_employee_id() {
        //given
        Employees employee = new Employees(1, "Leo", 18, "male", 1000, 1);
        when(repository.getEmployeeById(employee.getId())).thenReturn(employee);
        EmployeeService service = new EmployeeService(repository);
        //when
        Employees actual = service.findByID(employee.getId());
        //then
        assertEquals(employee.getId(), actual.getId());
    }

    @Test
    void should_return_5_employee_when_getByPage_given_employee_request() {
        //given
        List<Employees> employeesList = new ArrayList<>();
        employeesList.add(new Employees(1, "Angelo", 23, "male", 1000, 1));
        employeesList.add(new Employees(2, "Angela", 26, "female", 900, 1));
        employeesList.add(new Employees(3, "Leo", 18, "male", 1000, 1));
        employeesList.add(new Employees(4, "Axie", 26, "female", 900, 1));
        employeesList.add(new Employees(5, "Player", 18, "male", 1000, 1));

        when(repository.getByPage(1, 5)).thenReturn(employeesList);
        EmployeeService employeeService = new EmployeeService(repository);
        //when
        List<Employees> employeeActual = employeeService.getByPage(1, 5);
        //then
        assertEquals(5, employeeActual.size());
    }

    @Test
    void shoul_return_all_male_employees_when_gender_male_given_2_male() {
        //given
        String gender = "male";
        Employees male1;
        Employees male2;
        male1 = new Employees(1, "Angelo", 23, "male", 1000, 1);
        male2 = new Employees(2, "Angela", 26, "female", 900, 1);
        EmployeeService service = new EmployeeService(repository);
        when(repository.getGender(gender)).thenReturn(asList(male1, male2));

        //when
        List<Employees> actual = service.findByGender(gender);
        //then
        assertEquals(asList(male1, male2), actual);
    }

    @Test
    void should_update_employee_when_update_by_employee_id_given_employee_id() {
        //given
        Employees employee = new Employees(1, "Leo", 18, "male", 1000, 1);
        Employees updateEmployee = new Employees(1, "Leo", 18, "male", 2000, 1);
        when(repository.updateById(employee.getId(), employee)).thenReturn(updateEmployee);
        EmployeeService service = new EmployeeService(repository);
        //when
        Employees actual = service.updateById(employee.getId(), employee);
        //then
        assertNotEquals(employee.getSalary(), actual.getSalary());
    }

    @Test
    void should_delete_employee_when_delete_by_employee_id_given_employee() {
        //given
        Employees employee = new Employees(1, "Leo", 18, "male", 1000, 1);
        EmployeeService service = new EmployeeService(repository);
        //when
        service.deleteById(employee.getId());
        //then
        assertNull(repository.deleteById(employee.getId()));

    }


}
