package com.shopping.database.api.Database.API.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CartVsProduct {
    UUID cartId;
    UUID productId;
}
