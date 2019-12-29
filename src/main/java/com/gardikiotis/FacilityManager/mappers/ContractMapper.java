package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Contract;
import com.gardikiotis.FacilityManager.responses.ContractResponse;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {


 public ContractResponse mapContractToContractResponse(Contract contract) {
     return new ContractResponse(
             contract.getId(),
             contract.getContractor1(),
             contract.getContractor2(),
             contract.getStartingDate(),
             contract.getEndingDate(),
             contract.getAwardAmount()
     );


 }
}
