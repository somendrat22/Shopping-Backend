package com.shopping.backend.Front.API.DTO.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPRODUCTDTO {
    String productName;
    int price;
    int qunatity;
    UUID sellerId;
    String productType;
}
