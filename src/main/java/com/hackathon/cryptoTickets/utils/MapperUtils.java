package com.hackathon.cryptoTickets.utils;

import com.hackathon.cryptoTickets.collections.Company;
import com.hackathon.cryptoTickets.dto.CompanyDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<Company, CompanyDto> FromCompanyEntityToDto() {
        return company -> {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setAddress(company.getAddress());
            companyDto.setName(company.getName());
            companyDto.setEventHash(company.getEventHash());
            return companyDto;
        };
    }

    public Function<CompanyDto, Company> FromDtoToCompanyEntity(String id) {
        return companyDto -> {
            Company company = new Company();
            company.setId(id);
            company.setAddress(companyDto.getAddress());
            company.setName(companyDto.getName());
            company.setEventHash(companyDto.getEventHash());
            return company;
        };
    }
}
