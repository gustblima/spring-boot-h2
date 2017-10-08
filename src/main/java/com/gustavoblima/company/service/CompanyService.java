package com.gustavoblima.company.service;

import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.exception.IndustryNotFoundException;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> findCompanies(String name);

    CompanyDTO createCompany(CompanyDTO company) throws IndustryNotFoundException;

    CompanyDTO findCompany(Long id);

}
