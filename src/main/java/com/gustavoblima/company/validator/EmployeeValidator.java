package com.gustavoblima.company.validator;

import com.gustavoblima.company.converter.GenderConverter;
import com.gustavoblima.company.dto.EmployeeDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployeeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDTO employeeDTO = (EmployeeDTO) target;

        if(employeeDTO.getGender() == null){
            errors.rejectValue("gender", "Gender should not be equals null");
        }
        if(employeeDTO.getCpf().length() != 11 && !employeeDTO.getCpf().matches("[0-9]+")){
            errors.rejectValue("cpf", "Invalid CPF value");
        }
        if(employeeDTO.getEmail() == null){
            errors.rejectValue("email", "Email should not be equals null");
        }
        if(employeeDTO.getName() == null){
            errors.rejectValue("name", "Name should not be equals null");
        }
        if(employeeDTO.getEmployerId() == null){
            errors.rejectValue("employerId", "EmployerId should not be equals null");
        }
        try {
            new GenderConverter().convertToEntityAttribute(employeeDTO.getGender());
        }catch (IllegalArgumentException e){
            errors.rejectValue("gender", "Invalid gender value");
        }

    }
}
