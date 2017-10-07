package com.gustavoblima.company.service;

import com.gustavoblima.company.entity.Company;

import java.util.List;

public interface ICompanyService  {

    List<Company> findCompanies(String name);

    Company createCompany(Company company);


}
