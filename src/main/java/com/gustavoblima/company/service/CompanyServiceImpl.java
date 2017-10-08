package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.CompanyDTO;

import com.gustavoblima.company.dto.EmployeeDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Industry;
import com.gustavoblima.company.exception.CompanyNotFoundException;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import com.gustavoblima.company.repository.CompanyRepository;
import com.gustavoblima.company.repository.EmployeeRepository;
import com.gustavoblima.company.repository.IndustryRepository;
import com.gustavoblima.company.util.CompanyEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyEmployeeMapper mapper;

    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public Page<CompanyDTO> findCompanies(String name, Pageable pageable) {
        List<CompanyDTO> companies = mapper.companiesToCompanyDTOs(companyRepository.findFiltered(name, pageable));
        return new PageImpl<CompanyDTO>(companies, pageable, companies.size());
    }

    @Override
    public Page<EmployeeDTO> findCompanyEmployees(Long companyId, Pageable pageable) throws CompanyNotFoundException {
        Company company = companyRepository.findOne(companyId);
        if(company == null){
            throw new CompanyNotFoundException(companyId);
        }
        List<EmployeeDTO> employees = mapper.employeesToEmployeesDTOs(employeeRepository.findByEmployerId(companyId, pageable));
        return new PageImpl<EmployeeDTO>(employees, pageable, employees.size());
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO company) throws IndustryNotFoundException {

        Industry industry = industryRepository.findOne(company.getIndustryId());
        if(industry == null){
            throw new IndustryNotFoundException(company.getIndustryId());
        }
        Company companyEntity = mapper.companyDTOtoCompany(company);
        companyEntity.setId(null);
        companyEntity.setIndustry(industry);
        return new CompanyDTO(companyRepository.saveAndFlush(companyEntity).getId());
    }

    @Override
    public CompanyDTO findCompany(Long id) {
        return mapper.companyToCompanyDTO(companyRepository.findOne(id));
    }


}
