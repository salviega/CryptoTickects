package com.hackathon.cryptoTickets.repositories;

import com.hackathon.cryptoTickets.collections.Company;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {
}
