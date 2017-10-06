package com.gustavoblima.company.controller;

import com.gustavoblima.company.model.Company;
import com.gustavoblima.company.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @RequestMapping(method = RequestMethod.GET)
    Collection<Company> getEmployees(@RequestParam(value = "job", required = false) String job){

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{employeeId}")
    Company getEmployee(@PathVariable Long employeeId){

        return  null;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseBody registerEmployee(@RequestBody Employee employee){

        return null;
    }
}
