package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyCustomRepository {

    List<Company> findByIndustryId(Long industryId, Pageable pageable);
}
