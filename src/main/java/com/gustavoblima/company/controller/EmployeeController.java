package com.gustavoblima.company.controller;

import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.dto.EmployeeDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.exception.CompanyNotFoundException;
import com.gustavoblima.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Collection<EmployeeDTO>>getEmployees(@RequestParam(value = "job", required = false) String job){
         return new ResponseEntity<Collection<EmployeeDTO>>(employeeService.findEmployees(job), HttpStatus.OK);
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
    ResponseEntity<EmployeeDTO> registerEmployee(@RequestBody EmployeeDTO employee) throws CompanyNotFoundException{
        return new ResponseEntity<EmployeeDTO>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }
}
