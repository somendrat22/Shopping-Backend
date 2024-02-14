package com.shopping.database.api.Database.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReqBody {
    String productName;
    int price;
    int qunatity;
    String productType;
    UUID sellerId;
}
