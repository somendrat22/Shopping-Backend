package com.shopping.backend.user.User.API.Service;

import com.shopping.backend.user.User.API.DTO.GeneralUseDTO.UserDTO;
import com.shopping.backend.user.User.API.DTO.RequestBody.SellerProductRegistrationDTO;
import com.shopping.backend.user.User.API.Exceptions.UnAuthorized;
import com.shopping.backend.user.User.API.Exceptions.UserDoesNotExistException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class SellerService {

    public SellerProductRegistrationDTO register(SellerProductRegistrationDTO sellerProductRegistrationDTO){
        UUID sellerID = sellerProductRegistrationDTO.getSellerId();
        // I need to check that this user is a registered user or not
        // If it is not i will raise the exception
        // If it is registered user i will check this user is a seller or not
        // If it is not seller i am going to throw unAuthorized exception
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        String url = "http://localhost:8083/db/user/getuser?id=" + sellerID.toString();
        ResponseEntity<UserDTO> userResp = restTemplate.exchange(url, HttpMethod.GET, httpEntity, UserDTO.class);

        if(userResp.getBody() == null){
            throw new UserDoesNotExistException(String.format("User with id %s does not exist in system.", sellerID.toString()));
        }
        if(!userResp.getBody().getType().equals("SELLER")){
            throw new UnAuthorized(String.format("User with id %s does not have access of this operation", sellerID.toString()));
        }

        // Database API -> to save the prodcut

        String urlProduct = "http://localhost:8083/db/product/add";

        HttpEntity httpEntityP = new HttpEntity(sellerProductRegistrationDTO, httpHeaders);

        ResponseEntity<SellerProductRegistrationDTO> respProduct = restTemplate.exchange(urlProduct, HttpMethod.POST, httpEntityP, SellerProductRegistrationDTO.class);

        if(respProduct.getBody() == null){
            throw new RuntimeException(String.format("Product addition was not successfully !!"));
        }

        return respProduct.getBody();
    }
}
