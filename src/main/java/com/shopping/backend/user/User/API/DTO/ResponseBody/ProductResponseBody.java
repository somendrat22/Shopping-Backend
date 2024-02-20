package com.shopping.backend.user.User.API.DTO.ResponseBody;

import com.shopping.backend.user.User.API.DTO.GeneralUseDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseBody {
    UUID id;
    String productName;
    int price;
    int qunatity;
    UserDTO seller;
    double rating;
    String productType;
}
