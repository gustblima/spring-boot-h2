package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
    @Query("select c from Company c where c.name like %:jobTitle%")
    List<Employee> findByJobTitleContaining(@Param("jobTitle") String jobTitle);
}
