package com.gustavoblima.company.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long industryId;
    String title;

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @OneToMany
    private List<Company> companies = new ArrayList<>();
}
