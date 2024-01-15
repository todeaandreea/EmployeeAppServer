package com.utcn.employeeApplication.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(String name);

    List<Employee> findByDepartmentID(Integer departmentID);

    List<Employee> findByDepartmentIDAndManagerIDIsNotNull(Integer departmentID);
}
