package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface CompanyCustomRepository {

    List<Company> findFiltered(String name, Pageable pageable);
}
