package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Company;
import com.gardikiotis.FacilityManager.models.Customer;
import com.gardikiotis.FacilityManager.models.Facility;
import com.gardikiotis.FacilityManager.responses.CompanyResponse;
import com.gardikiotis.FacilityManager.responses.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private CompanyMapper mapper;
    public CustomerResponse mapCustomerToCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
         new CompanyResponse(
                customer.getCompany().getId(),
                customer.getCompany().getName(),
                customer.getCompany().getAfm(),
                customer.getCompany().getAddressStreet(),
                customer.getCompany().getAddressNumber(),
                customer.getCompany().getAddressPostCode(),
                customer.getCompany().getPhoneNumber(),
                customer.getCompany().getEmail()
        )

        );
    }
}
