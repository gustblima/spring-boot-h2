package com.gustavoblima.company.service;

import com.gustavoblima.company.entity.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> findEmployees();

    List<Employee> findEmployees(String jobTitle);

    Employee createEmployee(Employee employee);

}
