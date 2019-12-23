package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
