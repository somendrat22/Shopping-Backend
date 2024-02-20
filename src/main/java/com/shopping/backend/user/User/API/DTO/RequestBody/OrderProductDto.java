package com.shopping.backend.user.User.API.DTO.RequestBody;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductDto {
    UUID productId;
    int quantity;
}
