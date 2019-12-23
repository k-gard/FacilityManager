package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Company;
import com.gardikiotis.FacilityManager.responses.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse mapCompanyToCompanyResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName()
        );
    }

}

