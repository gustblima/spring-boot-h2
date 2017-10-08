package com.gustavoblima.company.controller;
import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.entity.Industry;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import com.gustavoblima.company.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Collection;

@RestController
@RequestMapping("/industries")
public class IndustryController {

    @Autowired
    IndustryService industryService;

    @RequestMapping(method = RequestMethod.GET, value = "/{industryId}/companies")
    ResponseEntity<Collection<CompanyDTO>> getRelatedCompanies(@PathVariable Long industryId) throws IndustryNotFoundException {
       return new ResponseEntity<Collection<CompanyDTO>>(industryService.findCompaniesByIndustry(industryId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Collection<Industry>> getIndustries(){
        return new ResponseEntity<Collection<Industry>>(industryService.findIndustries(), HttpStatus.OK);

    }

}
