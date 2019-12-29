package com.gardikiotis.FacilityManager;

import com.gardikiotis.FacilityManager.models.Company;
import com.gardikiotis.FacilityManager.models.Contractor;
import com.gardikiotis.FacilityManager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacilityManagerApplication implements CommandLineRunner {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	ContractorRepository contractorRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	EmployeeRepository EmployeeRepository;


	public static void main(String[] args) {
		SpringApplication.run(FacilityManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	Company c0 =new Company("Company 0",123456789,"Address 0","0","1234",1234509876,"email@emil.com");
	Company c1 =new Company("Company 1",123456789,"Address 1","1","1234",1234509876,"email@emil.com");
	Company c2 =new Company("Company 2",123456789,"Address 2","2","1234",1234509876,"email@emil.com");
	Company c3 =new Company("Company 3",123456789,"Address 3","3","1234",1234509876,"email@emil.com");
	Company c4 =new Company("Company 4",123456789,"Address 4","4","1234",1234509876,"email@emil.com");

	companyRepository.save(c1);
	companyRepository.save(c2);
	companyRepository.save(c3);
	companyRepository.save(c4);

	Contractor con1= new Contractor(c1);
	Contractor con2= new Contractor(c2);
	Contractor con3= new Contractor(c3);

	contractorRepository.save(con1);
	contractorRepository.save(con2);
	contractorRepository.save(con3);
	}
}
