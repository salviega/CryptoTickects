package com.hackathon.cryptoTickets.use_case;

import com.hackathon.cryptoTickets.dto.CompanyDto;
import com.hackathon.cryptoTickets.repositories.CompanyRepository;
import com.hackathon.cryptoTickets.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateCompanyUseCase implements ISaveCompany {

    private final CompanyRepository companyRepository;
    private final MapperUtils mapperUtils;

    public CreateCompanyUseCase(CompanyRepository companyRepository, MapperUtils mapperUtils) {
        this.companyRepository = companyRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<CompanyDto> apply(CompanyDto companyDto) {
        return companyRepository
                .save(this.mapperUtils.FromDtoToCompanyEntity(null).apply(companyDto))
                .map(this.mapperUtils.FromCompanyEntityToDto());
    }
}
