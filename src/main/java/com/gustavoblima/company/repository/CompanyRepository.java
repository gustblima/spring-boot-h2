package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from Company c where lower(c.name) like concat('%', :name, '%')")
    List<Company> findByNameContaining(@Param("name") String name);

}
