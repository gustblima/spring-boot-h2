package com.gustavoblima.company.repository;

import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Industry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyCustomRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Company> findFiltered(String name, Pageable pageable) {
        CriteriaBuilder builder =  entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);
        Root<Company> root = query.from(Company.class);
        List<Predicate> predicateds = new ArrayList<Predicate>();
        if(name != null){
            predicateds.add(builder.like(builder.lower(root.<String>get("name")), "%"+name.toLowerCase()+"%"));
        }
        query.select(root).where(predicateds.toArray(new Predicate[]{}));
        Query q = entityManager.createQuery(query);
        q.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        q.setMaxResults(pageable.getPageSize());
        List<Company> companies = q.getResultList();

        return q.getResultList();
    }
}
