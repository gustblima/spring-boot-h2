package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.EmployeeDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.exception.CompanyNotFoundException;
import com.gustavoblima.company.repository.CompanyRepository;
import com.gustavoblima.company.repository.EmployeeRepository;
import com.gustavoblima.company.util.CompanyEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyEmployeeMapper mapper;

    @Override
    public List<EmployeeDTO> findEmployees(String jobTitle) {
        return mapper.employeesToEmployeesDTOs(employeeRepository.findFiltered(jobTitle));
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employee) throws CompanyNotFoundException{
        Company company = companyRepository.findOne(employee.getEmployerId());
        if(company == null){
            throw new CompanyNotFoundException(employee.getEmployerId());
        }
        Employee employeeEntity = mapper.employeeDtoToEmployee(employee);
        employeeEntity.setEmployer(company);
        return mapper.employeeToEmployeeDTO(employeeRepository.saveAndFlush(employeeEntity));
    }

    @Override
    public EmployeeDTO findEmployee(Long id) {
        return mapper.employeeToEmployeeDTO(employeeRepository.findOne(id));
    }
}
