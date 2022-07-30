package com.hackathon.cryptoTickets.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Company {

    @Id
    private String id;
    private String adress;
    private String name;
    private String eventHash;

}
