package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
