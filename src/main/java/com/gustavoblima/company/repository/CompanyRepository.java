package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyCustomRepository {
}
