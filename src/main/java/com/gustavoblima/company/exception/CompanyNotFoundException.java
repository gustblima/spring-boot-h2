package com.gustavoblima.company.exception;

import javassist.NotFoundException;

public class CompanyNotFoundException extends NotFoundException {


    public CompanyNotFoundException(Long id){
        super("Company not found for id " + id);
    }
}
