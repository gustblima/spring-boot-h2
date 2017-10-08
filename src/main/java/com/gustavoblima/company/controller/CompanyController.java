package com.gustavoblima.company.controller;

import com.gustavoblima.company.dto.ApiErrorDTO;
import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import com.gustavoblima.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<CompanyDTO>> getCompanies(@RequestParam(value = "name", required = false) String name){
        return new ResponseEntity<Collection<CompanyDTO>>(companyService.findCompanies(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompanyDTO> getCompany(@PathVariable Long companyId){
        CompanyDTO companyDTO = companyService.findCompany(companyId);
        if(companyDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<CompanyDTO>(companyDTO, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<CompanyDTO> registerCompany(@RequestBody CompanyDTO company) throws IndustryNotFoundException{
       return new ResponseEntity<CompanyDTO>(companyService.createCompany(company), HttpStatus.CREATED);
    }
}
