package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.EmployeeDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.exception.CompanyNotFoundException;
import com.gustavoblima.company.repository.CompanyRepository;
import com.gustavoblima.company.repository.EmployeeRepository;
import com.gustavoblima.company.util.CompanyEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyEmployeeMapper mapper;

    @Override
    public Page<EmployeeDTO> findEmployees(String jobTitle, Pageable pageable) {
        List<EmployeeDTO> employees = mapper.employeesToEmployeesDTOs(employeeRepository.findFiltered(jobTitle, pageable));
        return new PageImpl<EmployeeDTO>(employees, pageable, employees.size());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employee) throws CompanyNotFoundException{
        Company company = companyRepository.findOne(employee.getEmployerId());
        if(company == null){
            throw new CompanyNotFoundException(employee.getEmployerId());
        }
        Employee employeeEntity = mapper.employeeDtoToEmployee(employee);
        employeeEntity.setEmployer(company);
        employeeEntity.setId(null);
        employeeEntity = employeeRepository.saveAndFlush(employeeEntity);
        return new EmployeeDTO(employeeEntity.getId());
    }

    @Override
    public EmployeeDTO findEmployee(Long id) {
        return mapper.employeeToEmployeeDTO(employeeRepository.findOne(id));
    }
}
