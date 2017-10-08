package com.gustavoblima.company.validator;

import com.gustavoblima.company.dto.CompanyDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CompanyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CompanyDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CompanyDTO companyDTO = (CompanyDTO) target;

        if(companyDTO.getCnpj().length() != 14 && !companyDTO.getCnpj().matches("[0-9]+")){
            errors.rejectValue("CNPJ", "Invalid CNPJ value");
        }
        if(companyDTO.getName() == null){
            errors.rejectValue("name", "Name should not be equals null");
        }
        if(companyDTO.getIndustryId() == null){
            errors.rejectValue("IndustryId", "Industry should not be equals null");
        }
    }
}
