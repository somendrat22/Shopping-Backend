package com.shopping.database.api.Database.API.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

// User -> One user can own many products -> One to Many
// product -> many to one

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    UUID id;
    String productName;
    int price;
    int qunatity;
    PortalUser seller;
    double rating;
    String productType;
}
