package com.gustavoblima.company.controller;

import com.gustavoblima.company.model.Company;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @RequestMapping(method = RequestMethod.GET)
    Collection<Company> getCompanies(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "industry", required = false) String industry){

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{companyId}")
    Company getCompany(@PathVariable Long companyId){

        return  null;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseBody registerCompany(@RequestBody Company company){

        return null;
    }
}
