package com.hackathon.cryptoTickets.use_case;

import com.hackathon.cryptoTickets.dto.CompanyDto;
import com.hackathon.cryptoTickets.repositories.CompanyRepository;
import com.hackathon.cryptoTickets.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetCompanyUseCase implements Function<String, Mono<CompanyDto>> {

    private final CompanyRepository companyRepository;
    private final MapperUtils mapperUtils;

    public GetCompanyUseCase(CompanyRepository companyRepository, MapperUtils mapperUtils) {
        this.companyRepository = companyRepository;
        this.mapperUtils = mapperUtils;
    }
    @Override
    public Mono<CompanyDto> apply(String id) {
        Objects.requireNonNull(id, "The id is required");
        return companyRepository.findById(id)
                .map(mapperUtils.FromCompanyEntityToDto());
    }
}
