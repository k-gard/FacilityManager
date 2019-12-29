package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.WorkOrderTask;
import com.gardikiotis.FacilityManager.responses.WorkOrderTaskResponse;
import org.springframework.stereotype.Component;

@Component
public class WorkOrderTaskMapper {
    public WorkOrderTaskResponse mapWorkOrderTaskToWorkOrderTaskResponse(WorkOrderTask workOrderTask){
        return new WorkOrderTaskResponse(
                workOrderTask.getId(),
                workOrderTask.getEmployeeList(),
                workOrderTask.getSubcontractorList(),
                workOrderTask.getTask(),
                workOrderTask.getStartingTime(),
                workOrderTask.getEndingTime(),
                workOrderTask.getHourDuration()
        );
    }
}
