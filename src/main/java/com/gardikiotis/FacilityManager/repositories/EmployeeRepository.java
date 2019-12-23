package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
