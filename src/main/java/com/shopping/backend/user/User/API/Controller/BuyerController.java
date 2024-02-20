package com.shopping.backend.user.User.API.Controller;

import com.shopping.backend.user.User.API.DTO.RequestBody.OrderDto;
import com.shopping.backend.user.User.API.utils.ShoppingLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/buyer")
public class BuyerController {

    @PostMapping("/order")
    public ResponseEntity placeOrder(@RequestBody OrderDto orderDto){
        ShoppingLogger.logger.info("Request Recieved : Buyer Controller : " + orderDto.toString());
        // call service
    }
}
