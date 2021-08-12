package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepo extends JpaRepository<Employees, Integer>{

}


//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Integer>
//}
