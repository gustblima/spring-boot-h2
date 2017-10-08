package com.gustavoblima.company.util;

import com.gustavoblima.company.dto.CompanyDTO;
import com.gustavoblima.company.dto.EmployeeDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CompanyEmployeeMapper {

    @Mappings({
        @Mapping(target = "industry", source = "industry.title"),
        @Mapping(target="industryId", source = "industry.id")
    })
    CompanyDTO companyToCompanyDTO(Company company);

    @InheritInverseConfiguration
    Company companyDTOtoCompany(CompanyDTO company);

    List<CompanyDTO> companiesToCompanyDTOs(List<Company> companies);

    @Mappings({
            @Mapping(target = "employer", source = "employer.name"),
            @Mapping(target = "employerId", source = "employer.id")
    })
    EmployeeDTO employeeToEmployeeDTO(Employee employeeDTO);

    List<EmployeeDTO> employeesToEmployeesDTOs(List<Employee> companies);

    @InheritInverseConfiguration
    Employee employeeDtoToEmployee(EmployeeDTO employee);
}
