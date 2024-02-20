package com.shopping.backend.user.User.API.DTO.RequestBody;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    UUID userId;
    List<OrderProductDto> orderProductDtoList;
}
