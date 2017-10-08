package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Employee;

import java.util.List;

public interface EmployeeCustomRepository {

    List<Employee> findFiltered(String jobTitle);
}
