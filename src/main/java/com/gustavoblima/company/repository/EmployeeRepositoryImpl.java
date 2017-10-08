package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Employee;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Employee> findFiltered(String jobTitle) {
        CriteriaBuilder builder =  entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);

        List<Predicate> predicateds = new ArrayList<Predicate>();
        if(jobTitle != null){
            predicateds.add(builder.like(builder.lower(root.<String>get("jobTitle")), "%" + jobTitle.toLowerCase() + "%"));
        }
        query.select(root).where(predicateds.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }
}
