package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Industry;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import com.gustavoblima.company.repository.CompanyRepository;
import com.gustavoblima.company.repository.IndustryRepository;
import com.gustavoblima.company.util.CompanyEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryServiceImpl implements  IndustryService {

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyEmployeeMapper mapper;

    @Override
    public Page<CompanyDTO> findCompaniesByIndustry(Long industryId, Pageable pageable) throws IndustryNotFoundException {
        Industry industry = industryRepository.findOne(industryId);
        if(industry == null){
            throw new IndustryNotFoundException(industryId);
        }
        List<CompanyDTO> companies = mapper.companiesToCompanyDTOs(companyRepository.findByIndustryId(industryId, pageable));
        return  new PageImpl<CompanyDTO>(companies, pageable, companies.size());
    }

    @Override
    public Page<Industry> findIndustries(Pageable pageable) {
        return industryRepository.findAll(pageable);
    }


}
