package com.gustavoblima.company.service;

import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService implements  IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findEmployees(String jobTitle) {
        return employeeRepository.findByJobTitleContaining(jobTitle);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }
}
