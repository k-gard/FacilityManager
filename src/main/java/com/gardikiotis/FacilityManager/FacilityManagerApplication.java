package com.gardikiotis.FacilityManager;

import com.gardikiotis.FacilityManager.models.Company;
import com.gardikiotis.FacilityManager.models.Contract;
import com.gardikiotis.FacilityManager.models.Contractor;
import com.gardikiotis.FacilityManager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
	@Autowired
	ContractRepository contractRepository;

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

	companyRepository.save(c0);
	companyRepository.save(c1);
	companyRepository.save(c2);
	companyRepository.save(c3);
	companyRepository.save(c4);

	Contractor con0= new Contractor(c0);

	Contractor con1= new Contractor(c1);
	Contractor con2= new Contractor(c2);
	Contractor con3= new Contractor(c3);

	contractorRepository.save(con0);
/*	contractorRepository.save(con1);
	contractorRepository.save(con2);
	contractorRepository.save(con3);*/
	/*Contractor[] contractors1=new Contractor[2];
	Contractor[] contractors2=new Contractor[2];*/
/*

	contractors1[0]=con0;
	contractors1[1]=con1;
	contractors1[0]=con0;
	contractors1[1]=con2;
*/

	Set<Contractor> contractors = new HashSet();

	contractors.add(con0);
	contractors.add(con1);

	Set<Contractor> contractors2 = new HashSet();

	Contract contract1 =new Contract(contractors,LocalDate.of(2020, 1, 1),LocalDate.of(2020,1,31),2000);
	Contract contract2 =new Contract(contractors2,LocalDate.of(2020, 1, 1),LocalDate.of(2020,1,31),2000);

	contractRepository.save(contract1);
	contractRepository.save(contract2);


	}
}
