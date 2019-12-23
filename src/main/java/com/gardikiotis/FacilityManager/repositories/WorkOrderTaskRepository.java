package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.WorkOrderTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderTaskRepository extends CrudRepository<WorkOrderTask, Long> {
}
