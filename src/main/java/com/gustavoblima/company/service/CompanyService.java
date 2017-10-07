package com.gustavoblima.company.service;

import com.gustavoblima.company.entity.Company;

import com.gustavoblima.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements  ICompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> findCompanies(String name) {

        if(name == null){
            return companyRepository.findAll();
        }
        return companyRepository.findByNameContaining(name);
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }
}
