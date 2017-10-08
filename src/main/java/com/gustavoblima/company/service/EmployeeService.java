package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.EmployeeDTO;
import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.exception.CompanyNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findEmployees(String jobTitle);

    EmployeeDTO createEmployee(EmployeeDTO employee) throws CompanyNotFoundException;

    EmployeeDTO findEmployee(Long id);

}
