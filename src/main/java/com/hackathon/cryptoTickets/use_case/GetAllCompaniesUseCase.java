package com.hackathon.cryptoTickets.use_case;

import com.hackathon.cryptoTickets.dto.CompanyDto;
import com.hackathon.cryptoTickets.repositories.CompanyRepository;
import com.hackathon.cryptoTickets.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllCompaniesUseCase implements Supplier<Flux<CompanyDto>> {

    private final CompanyRepository companyRepository;
    private final MapperUtils mapperUtils;

    public GetAllCompaniesUseCase(CompanyRepository companyRepository, MapperUtils mapperUtils) {
        this.companyRepository = companyRepository;
        this.mapperUtils = mapperUtils;
    }
    @Override
    public Flux<CompanyDto> get() {
        return companyRepository.findAll()
                .map(mapperUtils.FromCompanyEntityToDto());
    }
}
