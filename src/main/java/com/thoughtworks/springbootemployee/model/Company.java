package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyNumber;
    private String companyName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<Employees> employeesList;

    public Company(Integer companyNumber, String companyName, List<Employees> employeesList) {
        this.companyNumber = companyNumber;
        this.companyName = companyName;
        this.employeesList = employeesList;
    }

    public Integer getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(Integer companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }
}
