package com.hackathon.cryptoTickets.routers;

import com.hackathon.cryptoTickets.dto.CompanyDto;
import com.hackathon.cryptoTickets.use_case.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QueryRouter {

    Logger log = LoggerFactory.getLogger("QueryRouter");

    @Bean
    public RouterFunction<ServerResponse> getAllCompanies(GetAllCompaniesUseCase getAllCompaniesUseCase) {
        return route(GET("/companies").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllCompaniesUseCase.get(), CompanyDto.class))

        );
    }

    @Bean
    public RouterFunction<ServerResponse> getCompany(GetCompanyUseCase getCompanyUseCase) {
        return route(GET("/companies/{address}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCompanyUseCase.apply(request.pathVariable("address")),
                                CompanyDto.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> createCompany(CreateCompanyUseCase createCompanyUseCase) {
        return route(POST("/companies").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CompanyDto.class)
                        .flatMap(createCompanyDto -> createCompanyUseCase.apply(createCompanyDto)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateCompany(UpdateCompanyUseCase updateCompanyUseCase) {
        return route(PUT("/companies/{address}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CompanyDto.class)
                        .flatMap(updateCyclistDto -> updateCompanyUseCase.apply(request.pathVariable("address"), updateCyclistDto)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteCompany(DeleteCompanyUseCase deleteCompanyUseCase) {
        return route(DELETE("/companies/{address}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteCompanyUseCase.apply(request.pathVariable("address")), Void.class))
        );
    }
}
