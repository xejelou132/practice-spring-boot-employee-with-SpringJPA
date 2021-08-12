package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private EmployeesRepo employeeRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void should_return_list_of_employees_when_get_all_employees_given_get_request() throws Exception {
        //given
        Employee employee = new Employee(1, "joseph", 22, "male", 1000000 , 1);
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("joseph"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(1000000));
    }

    @Test
    void should_create_employee_when_create_given_employee_request() throws Exception {
        // given
        String employeeAsJson = "{\n" +
                "  \"name\": \"joseph\",\n" +
                "  \"age\": 22,\n" +
                "  \"gender\": \"male\",\n" +
                "  \"salary\": 1000000\n" +
                "}";

        // when
        // then
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("joseph"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(1000000));

        List<Employee> employees = employeeRepository.findAll();
        Assertions.assertEquals(4, employees.size());
    }

    @Test
    void should_return_employee_when_get_specific_employee_given_get_employee_request() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee(1, "joseph", 22, "male", 1000000 , 1));

        //when
        //then
        mockMvc.perform(get("/employees/" + employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("joseph"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(1000000));
    }
    
}
