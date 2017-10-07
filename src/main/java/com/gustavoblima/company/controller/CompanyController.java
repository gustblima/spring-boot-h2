package com.gustavoblima.company.controller;

import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.service.ICompanyService;
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
    ICompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Company>> getCompanies(@RequestParam(value = "name", required = false) String name){
        try{
            return new ResponseEntity<Collection<Company>>(companyService.findCompanies(name), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Company getCompany(@PathVariable Long companyId){

        return  null;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<Company> registerCompany(@RequestBody Company company){
        try{
            return new ResponseEntity<Company>(companyService.createCompany(company), HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
