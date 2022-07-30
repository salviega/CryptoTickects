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
    public Mono<CompanyDto> apply(String id, CompanyDto companyDto) {
        Objects.requireNonNull(id, "Id is required");
        return getCompanyUseCase.apply(id)
                .flatMap(foundCompanyDto -> {
                    foundCompanyDto.setId(companyDto.getId());
                    foundCompanyDto.setEventHash(companyDto.getEventHash());
                    return companyRepository
                            .save(mapperUtils.FromDtoToCompanyEntity(id).apply(companyDto))
                            .map(mapperUtils.FromCompanyEntityToDto());
                });
    }

}
