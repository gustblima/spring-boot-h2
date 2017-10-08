package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.entity.Industry;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IndustryService {

    Page<CompanyDTO> findCompaniesByIndustry(Long industryId, Pageable pageable) throws IndustryNotFoundException;

    Page<Industry> findIndustries(Pageable pageable);
}
