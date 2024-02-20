package com.shopping.backend.user.User.API.Service;

import com.shopping.backend.user.User.API.DTO.RequestBody.OrderDto;
import com.shopping.backend.user.User.API.DTO.RequestBody.OrderProductDto;
import com.shopping.backend.user.User.API.DTO.RequestBody.SellerProductRegistrationDTO;
import com.shopping.backend.user.User.API.DTO.ResponseBody.ProductResponseBody;
import com.shopping.backend.user.User.API.utils.ApiUrlUtil;
import com.shopping.backend.user.User.API.utils.RequesterUtil;
import com.shopping.backend.user.User.API.utils.ShoppingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {

    @Autowired
    RequesterUtil requesterUtil;

    /*
        Will call DB Api one by one to get all products details
        According to every product we will check are we able to place order or not
        If any product is having the less quantity our api will provide response accordingly
     */
    public void orderProduct(OrderDto orderDto){
        ShoppingLogger.logger.info("Inside Service Layer will call db api to get all product details");
        List<OrderProductDto> orderProducts = orderDto.getOrderProductDtoList();
        List<ProductResponseBody> products = new ArrayList<>();
        for(OrderProductDto orderProductDto : orderProducts){
            UUID productId = orderProductDto.getProductId();
            // We will call db api to get specific product details
            String url = ApiUrlUtil.dbApiURL + "/product/get" + "?productId=" + productId.toString();
            RestTemplate requestor = requesterUtil.getRestTemplate();
            HttpHeaders header = requesterUtil.getHeader();
            HttpEntity httpEntity = requesterUtil.createHttpEntity(header);
            ResponseEntity<ProductResponseBody> product = requestor.exchange(url, HttpMethod.GET, httpEntity, ProductResponseBody.class);
            products.add(product.getBody());
        }
        ShoppingLogger.logger.info("Got all the products present in the system");
        
    }
}
