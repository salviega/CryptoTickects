package com.hackathon.cryptoTickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private String id;
    @NotBlank(message = "The address is required")
    private String adress;
    @NotBlank(message = "The name is required")
    private String name;
    @NotBlank(message = "The company hash is required")
    private String eventHash;

}
