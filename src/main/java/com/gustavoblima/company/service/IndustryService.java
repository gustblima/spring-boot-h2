package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.entity.Industry;
import com.gustavoblima.company.exception.IndustryNotFoundException;

import java.util.List;

public interface IndustryService {

    List<CompanyDTO> findCompaniesByIndustry(Long industryId) throws IndustryNotFoundException;

    List<Industry> findIndustries();
}
