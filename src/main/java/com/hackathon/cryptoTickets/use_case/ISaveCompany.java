package com.hackathon.cryptoTickets.use_case;

import com.hackathon.cryptoTickets.dto.CompanyDto;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface ISaveCompany {

    Mono<CompanyDto> apply(@Valid CompanyDto companyDto);
}
