package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {

}
