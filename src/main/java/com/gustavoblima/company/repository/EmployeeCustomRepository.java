package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeCustomRepository {

    List<Employee> findFiltered(String jobTitle, Pageable pageable);
}
