package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private Integer companyNumber;
    private String companyName;
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }
}
