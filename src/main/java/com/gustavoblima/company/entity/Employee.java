package com.gustavoblima.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gustavoblima.company.converter.GenderConverter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_employee")
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2)
    private String name;

    @Convert(converter = GenderConverter.class)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER)
    private Company employer;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column(length = 14)
    @Size(min = 14, max = 14)
    private String cpf;

    @Column(length = 100)
    private String jobTitle;

    private String seed;

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.employer = employer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
}