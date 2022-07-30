package com.hackathon.cryptoTickets.utils;

import com.hackathon.cryptoTickets.collections.Company;
import com.hackathon.cryptoTickets.dto.CompanyDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<Company, CompanyDto> FromCompanyEntityToDto() {
        return company -> {
            var companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());
            companyDto.setEventHash(company.getEventHash());
            return companyDto;
        };
    }

    public Function<CompanyDto, Company> FromDtoToCompanyEntity(String id) {
        return CompanyDto -> {
            var company = new Company();
            company.setId(id);
            company.setName(CompanyDto.getName());
            company.setEventHash(CompanyDto.getEventHash());
            return company;
        };
    }
}
