package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeesTest {

    EmployeesRepo repository = Mockito.mock(EmployeesRepo.class);
    EmployeeResponse response = Mockito.mock(EmployeeResponse.class);
    EmployeeService service =  Mockito.mock(EmployeeService.class);


    @Test
    void shoul_return_all_employess_when_getallemployees_given_all_employess() {
        //given
        List<Employee> employeesList = new ArrayList<>();

        employeesList.add(new Employee("Angelo", 23, "male", 1000));
        employeesList.add(new Employee("Angela", 26, "female", 900));

        when(repository.findAll()).thenReturn(employeesList);
         service = new EmployeeService(repository);


        List<EmployeeResponse> actualEmployess = service.getEmployeesList();

        assertEquals(actualEmployess.size(), employeesList.size());
    }


    @Test
    void shoul_return_1_employess_when_add_employee_given_1_employee() {
        //given
        Employee employee = new Employee(1, "Leo", 18, "male", 1000);
        EmployeeService service = new EmployeeService(repository);
        when(repository.save(employee)).thenReturn(employee);
        //when
        Employee actual = service.addEmployee(employee);
        //then
        assertEquals(1, actual.getId());
    }

    @Test
    void should_get_employee_when_get_by_id_given_employee_id() {
        //given
        Employee employee = new Employee(1, "Leo", 18, "male", 1000);
        when(repository.findById(employee.getId())).thenReturn(java.util.Optional.of(employee));
        EmployeeService service = new EmployeeService(repository);
        //when
        //Employee actual = service.findByID(employee.getId());
        //then
       // assertEquals(employee.getId(), actual.getId());
    }

    @Test
    void should_return_5_employee_when_getByPage_given_employee_request() {
        //given
        Integer index = 5;
        Integer size = 1;
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(new Employee(1, "Angelo", 23, "male", 1000));
        employeesList.add(new Employee(2, "Angela", 26, "female", 900));
        employeesList.add(new Employee(3, "Leo", 18, "male", 1000));
        employeesList.add(new Employee(4, "Axie", 26, "female", 900));
        employeesList.add(new Employee(5, "Player", 18, "male", 1000));

        when(repository.findAll()).thenReturn(employeesList);

        EmployeeService employeeService = new EmployeeService(repository);
        //when
        //List<Employee> employeeActual = employeeService.getByPage(index, size);
        //then
      //  assertEquals(5, employeeActual.size());
    }

    @Test
    void shoul_return_all_male_employees_when_gender_male_given_2_male() {
        //given
        String gender = "male";
        Employee male1;
        Employee male2;
        male1 = new Employee(1, "Angelo", 23, "male", 1000);
        male2 = new Employee(2, "Angela", 26, "male", 900);
        EmployeeService service = new EmployeeService(repository);
        when(repository.findAll()
                .stream()
                .filter(employees1 -> employees1.getGender().equals(gender))
                .collect(Collectors.toList()))
                .thenReturn(asList(male1, male2));

        //when
        //List<Employee> actual = service.findByGender(gender);
        //then
  //      assertEquals(asList(male1, male2), actual);
    }

    @Test
    void should_update_employee_when_update_by_employee_id_given_employee_id() {
        //given
        Employee employee = new Employee(1, "Leo", 18, "male", 1000);
        Employee updateEmployee = new Employee(1, "Leo", 18, "male", 2000);
        // when(repository.(employee.getId(), employee)).thenReturn(updateEmployee);
        EmployeeService service = new EmployeeService(repository);
        //when
        Employee actual = service.updateById(employee.getId(), employee);
        //then
        //     assertNotEquals(employee.getSalary(), actual.getSalary());
    }

    @Test
    void should_delete_employee_when_delete_by_employee_id_given_employee() {
        //given
        Employee employee = new Employee(1, "Leo", 18, "male", 1000);
        EmployeeService service = new EmployeeService(repository);
        //when
        service.deleteById(employee.getId());
        //then
        assertNull(repository.findById(employee.getId()).orElse(null));
    }


}
