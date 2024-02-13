package com.shopping.database.api.Database.API.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderVsProduct {
    UUID orderId;
    UUID productID;
}
