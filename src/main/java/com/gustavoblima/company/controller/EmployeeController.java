package com.gustavoblima.company.controller;

import com.gustavoblima.company.dto.ApiErrorDTO;
import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.dto.EmployeeDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.exception.CompanyNotFoundException;
import com.gustavoblima.company.service.EmployeeService;
import com.gustavoblima.company.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Page<EmployeeDTO>>getEmployees(@RequestParam(value = "job", required = false) String job,
                                                  Pageable pageable){
         Page employees = employeeService.findEmployees(job, pageable);
         return new ResponseEntity<Page<EmployeeDTO>>(employees, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{employeeId}")
    ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.findEmployee(employeeId);
        if(employeeDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> registerEmployee(@RequestBody EmployeeDTO employee,
                                                 BindingResult result) throws CompanyNotFoundException {
        new EmployeeValidator().validate(employee, result);
        if(result.hasErrors()){
            return new ResponseEntity<ApiErrorDTO>(new ApiErrorDTO(result.getAllErrors()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<EmployeeDTO>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }
}
