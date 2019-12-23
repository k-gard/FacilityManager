package com.gardikiotis.FacilityManager.repositories;


import com.gardikiotis.FacilityManager.models.WorkOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long> {
}
