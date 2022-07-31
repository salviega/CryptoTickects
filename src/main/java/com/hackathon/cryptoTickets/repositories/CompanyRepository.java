package com.hackathon.cryptoTickets.repositories;

import com.hackathon.cryptoTickets.collections.Company;
import com.hackathon.cryptoTickets.dto.CompanyDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {
    public Mono<Company> findByAddress(String address);
    public Mono<Void> deleteByAddress(String address);
}
