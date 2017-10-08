package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeCustomRepository {

    List<Employee> findByEmployerId(Long companyId, Pageable pageable);
}
