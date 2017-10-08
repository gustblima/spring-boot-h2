package com.gustavoblima.company.util;

import com.gustavoblima.company.converter.GenderConverter;
import com.gustavoblima.company.dto.RUResultDTO;
import com.gustavoblima.company.dto.RUWrapperDTO;
import com.gustavoblima.company.entity.Company;
import com.gustavoblima.company.entity.Employee;
import com.gustavoblima.company.repository.CompanyRepository;
import com.gustavoblima.company.repository.EmployeeRepository;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public void run(String... args) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        RUWrapperDTO randomUserResult = restTemplate
                .getForObject("https://randomuser.me/api/?inc=gender,email,name,seed&results=10", RUWrapperDTO.class);

        String seed = randomUserResult.getInfo().getSeed();
        Random random = new Random();
        String[] titles = new String[]{"Developer", "Assistant", "Engineer", "CEO"};
        List<Company> companies = companyRepository.findAll();
        randomUserResult.getResults().forEach((RUResultDTO user) -> {
            Employee employee = new Employee();
            employee.setName(user.getName().getFullName());
            employee.setEmail(user.getEmail());
            employee.setGender(new GenderConverter().convertToEntityAttribute(user.getGender()));
            employee.setSeed(seed);
            employee.setEmployer(companies.get(random.nextInt(companies.size())));
            employee.setJobTitle(titles[random.nextInt(titles.length)]);

            // TODO arrumar o cpf
            employee.setCpf("46262462");
            employeeRepository.saveAndFlush(employee);
        });



    }
}
