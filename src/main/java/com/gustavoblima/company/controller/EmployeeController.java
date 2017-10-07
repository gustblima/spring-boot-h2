package com.gustavoblima.company.controller;

import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
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
