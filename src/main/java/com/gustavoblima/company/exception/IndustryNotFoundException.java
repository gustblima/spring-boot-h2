package com.gustavoblima.company.exception;

import javassist.NotFoundException;

public class IndustryNotFoundException extends NotFoundException {


    public IndustryNotFoundException(Long id){
        super("Industry not found for id " + id);
    }
}
