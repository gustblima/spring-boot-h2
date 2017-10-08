package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.entity.Industry;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import com.gustavoblima.company.repository.IndustryRepository;
import com.gustavoblima.company.util.CompanyEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryServiceImpl implements  IndustryService {

    @Autowired
    IndustryRepository industryRepository;

    @Autowired
    CompanyEmployeeMapper mapper;

    @Override
    public List<CompanyDTO> findCompaniesByIndustry(Long industryId) throws IndustryNotFoundException {
        Industry industry = industryRepository.findOne(industryId);
        if(industry == null){
            throw new IndustryNotFoundException(industryId);
        }
        return  mapper.companiesToCompanyDTOs(industry.getCompanies());
    }

    @Override
    public List<Industry> findIndustries() {
        return industryRepository.findAll();
    }


}
