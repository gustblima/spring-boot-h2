package com.gustavoblima.company.controller;
import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.entity.Industry;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import com.gustavoblima.company.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    ResponseEntity<Page<CompanyDTO>> getIndustryCompanies(@PathVariable Long industryId,
                                                          Pageable pageable) throws IndustryNotFoundException {
        Page<CompanyDTO> companies = industryService.findCompaniesByIndustry(industryId, pageable);
        return new ResponseEntity<Page<CompanyDTO>>(companies, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Page<Industry>> getIndustries(Pageable pageable){
        Page<Industry> industries = industryService.findIndustries(pageable);
        return new ResponseEntity<Page<Industry>>(industries, HttpStatus.OK);

    }

}
