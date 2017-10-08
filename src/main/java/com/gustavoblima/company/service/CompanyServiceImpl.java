package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.CompanyDTO;

import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Industry;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import com.gustavoblima.company.repository.CompanyRepository;
import com.gustavoblima.company.repository.IndustryRepository;
import com.gustavoblima.company.util.CompanyEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyEmployeeMapper mapper;

    @Autowired
    IndustryRepository industryRepository;

    @Override
    public List<CompanyDTO> findCompanies(String name) {
        return mapper.companiesToCompanyDTOs(companyRepository.findFiltered(name));
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
