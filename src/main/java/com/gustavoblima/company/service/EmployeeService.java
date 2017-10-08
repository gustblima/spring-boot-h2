package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.EmployeeDTO;
import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.exception.CompanyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Page<EmployeeDTO> findEmployees(String jobTitle, Pageable pageable);

    EmployeeDTO createEmployee(EmployeeDTO employee) throws CompanyNotFoundException;

    EmployeeDTO findEmployee(Long id);

}
