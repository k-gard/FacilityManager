package com.gardikiotis.FacilityManager.services;

import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.mappers.ContractMapper;
import com.gardikiotis.FacilityManager.models.Contract;
import com.gardikiotis.FacilityManager.responses.ContractResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.repositories.ContractRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class ContractService {

    private ContractMapper mapper;

    private ContractRepository repository;

    public ContractService(ContractMapper mapper, ContractRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse getAllContracts(){
        try {
            Iterable<Contract> retrievedContracts = repository.findAll();
            List<ContractResponse> Contracts = new ArrayList<>();
            for (Contract Contract : retrievedContracts
            ) {
                Contracts.add(mapper.mapContractToContractResponse(Contract));
            }
            if (Contracts.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Error", "Nothing found"));

            }
            return new GenericResponse<>(Contracts);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }


    }

    public GenericResponse<Contract> getContractById(long id){
        try {
            Optional<Contract> fetchedContract = repository.findById(id);
            Contract Contract= fetchedContract.get();
            return new GenericResponse<>(Contract);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Contract","Id : "+id+" does not exist" ));
        }
    }


    public GenericResponse<ContractResponse> createContract(Contract Contract) {
        repository.save(Contract);
        return new GenericResponse<>(mapper.mapContractToContractResponse(Contract));
    }

    public GenericResponse<ContractResponse> updateContract(long ContractId, Contract Contract) {
        Optional<Contract> fetchedContract = repository.findById(ContractId);
        if(!fetchedContract.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","Contract with id: "+ContractId+" does not exist"));

        }
        Contract retrievedContract= fetchedContract.get();
        Map<String, Object> ContractMap = new HashMap<>();
        Field[] allFields = Contract.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(Contract);
                    if (value != null&& !value.equals(0)) {
                        ContractMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        ContractMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Contract.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedContract, v);
            field.setAccessible(false);
        });

        repository.save(retrievedContract);
        return new GenericResponse<>(mapper.mapContractToContractResponse(retrievedContract));
    }
}
