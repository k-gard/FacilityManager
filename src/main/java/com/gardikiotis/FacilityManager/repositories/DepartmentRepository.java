package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
