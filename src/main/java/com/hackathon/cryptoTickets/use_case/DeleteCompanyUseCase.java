package com.hackathon.cryptoTickets.use_case;

import com.hackathon.cryptoTickets.repositories.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteCompanyUseCase implements Function<String, Mono<Void>> {

    private final CompanyRepository companyRepository;

    public DeleteCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Mono<Void> apply(String address) {
        Objects.requireNonNull(address, "Address is required");
        return companyRepository.deleteByAddress(address);
    }
}
