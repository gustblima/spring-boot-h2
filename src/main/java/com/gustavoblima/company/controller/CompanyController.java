package com.gustavoblima.company.controller;

import com.gustavoblima.company.dto.ApiErrorDTO;
import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.dto.EmployeeDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.exception.CompanyNotFoundException;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import com.gustavoblima.company.service.CompanyService;
import com.gustavoblima.company.validator.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<CompanyDTO>> getCompanies(@RequestParam(value = "name", required = false) String name,
                                                  Pageable pageable){
        return new ResponseEntity<Page<CompanyDTO>>(companyService.findCompanies(name, pageable), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompanyDTO> getCompany(@PathVariable Long companyId){
        CompanyDTO companyDTO = companyService.findCompany(companyId);
        if(companyDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<CompanyDTO>(companyDTO, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{companyId}/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<EmployeeDTO>> getCompanyEmployees(@PathVariable Long companyId,
                                                   Pageable pageable) throws CompanyNotFoundException{
        Page<EmployeeDTO> employees = companyService.findCompanyEmployees(companyId, pageable);
        return new ResponseEntity<Page<EmployeeDTO>>(employees, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<?> registerCompany(@RequestBody CompanyDTO company,
                                               BindingResult result) throws IndustryNotFoundException{
        new CompanyValidator().validate(company, result);
        if(result.hasErrors()){
            return new ResponseEntity<ApiErrorDTO>(new ApiErrorDTO(result.getAllErrors()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CompanyDTO>(companyService.createCompany(company), HttpStatus.CREATED);
    }
}
