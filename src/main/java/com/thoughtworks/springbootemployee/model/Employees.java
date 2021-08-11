package com.thoughtworks.springbootemployee.model;

public class Employees {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private Integer companyId;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Employees(Integer id, String name, Integer age, String gender, Integer salary, Integer companyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }


    public Integer getCompanyId() {
        return companyId;
    }

}
