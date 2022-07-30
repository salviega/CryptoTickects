package com.hackathon.cryptoTickets.use_case;

import com.hackathon.cryptoTickets.dto.CompanyDto;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface IUpdateCompany {
    public Mono<CompanyDto> apply(@Valid String id, CompanyDto cyclistDto);

}
