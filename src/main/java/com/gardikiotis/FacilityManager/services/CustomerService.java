package com.gardikiotis.FacilityManager.services;

import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.mappers.CustomerMapper;
import com.gardikiotis.FacilityManager.models.Customer;
import com.gardikiotis.FacilityManager.responses.CustomerResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class CustomerService {

    private CustomerMapper mapper;

    private CustomerRepository repository;

    public CustomerService(CustomerMapper mapper, CustomerRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse getAllCustomers(){
        try {
            Iterable<Customer> retrievedCustomers = repository.findAll();
            List<CustomerResponse> Customers = new ArrayList<>();
            for (Customer Customer : retrievedCustomers
            ) {
                Customers.add(mapper.mapCustomerToCustomerResponse(Customer));
            }
            if (Customers.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Error", "Nothing found"));

            }
            return new GenericResponse<>(Customers);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }


    }

    public GenericResponse<Customer> getCustomerById(long id){
        try {
            Optional<Customer> fetchedCustomer = repository.findById(id);
            Customer Customer= fetchedCustomer.get();
            return new GenericResponse<>(Customer);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Customer","Id : "+id+" does not exist" ));
        }
    }


    public GenericResponse<CustomerResponse> createCustomer(Customer Customer) {
        repository.save(Customer);
        return new GenericResponse<>(mapper.mapCustomerToCustomerResponse(Customer));
    }

    public GenericResponse<CustomerResponse> updateCustomer(long CustomerId, Customer Customer) {
        Optional<Customer> fetchedCustomer = repository.findById(CustomerId);
        if(!fetchedCustomer.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","Customer with id: "+CustomerId+" does not exist"));

        }
        Customer retrievedCustomer= fetchedCustomer.get();
        Map<String, Object> CustomerMap = new HashMap<>();
        Field[] allFields = Customer.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(Customer);
                    if (value != null&& !value.equals(0)) {
                        CustomerMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        CustomerMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Customer.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedCustomer, v);
            field.setAccessible(false);
        });

        repository.save(retrievedCustomer);
        return new GenericResponse<>(mapper.mapCustomerToCustomerResponse(retrievedCustomer));
    }
}

