package com.hackathon.cryptoTickets.use_case;

import com.hackathon.cryptoTickets.dto.CompanyDto;
import com.hackathon.cryptoTickets.repositories.CompanyRepository;
import com.hackathon.cryptoTickets.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateCompanyUseCase implements IUpdateCompany {

    private final CompanyRepository companyRepository;
    private final MapperUtils mapperUtils;
    private final GetCompanyUseCase getCompanyUseCase;
    public UpdateCompanyUseCase(CompanyRepository companyRepository, MapperUtils mapperUtils, GetCompanyUseCase getCompanyUseCase) {
        this.companyRepository = companyRepository;
        this.mapperUtils = mapperUtils;
        this.getCompanyUseCase = getCompanyUseCase;
    }

    @Override
    public Mono<CompanyDto> apply(String address, CompanyDto companyDto) {
        Objects.requireNonNull(address, "Address is required");
        return getCompanyUseCase.apply(address)
                .flatMap(foundCompanyDto -> {
                    foundCompanyDto.setName((companyDto.getName()));
                    foundCompanyDto.setEventHash(companyDto.getEventHash());
                    return companyRepository
                            .save(mapperUtils.FromDtoToCompanyEntity(foundCompanyDto.getId()).apply(foundCompanyDto))
                            .map(mapperUtils.FromCompanyEntityToDto());
                });
    }

}
