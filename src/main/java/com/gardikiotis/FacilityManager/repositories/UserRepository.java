package com.gardikiotis.FacilityManager.repositories;


import com.gardikiotis.FacilityManager.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
