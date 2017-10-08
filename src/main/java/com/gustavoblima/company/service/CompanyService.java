package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

    Page<CompanyDTO> findCompanies(String name, Pageable pageable);

    CompanyDTO createCompany(CompanyDTO company) throws IndustryNotFoundException;

    CompanyDTO findCompany(Long id);

}
