package com.shopping.backend.Front.API.Controller;

import com.shopping.backend.Front.API.DTO.GeneralMessageDTO;
import com.shopping.backend.Front.API.DTO.UserSignUpDTO;
import com.shopping.backend.Front.API.DTO.requestDTO.ACLConfigDTO;
import com.shopping.backend.Front.API.DTO.requestDTO.RequestPRODUCTDTO;
import com.shopping.backend.Front.API.DTO.requestDTO.SellerProductRegistrationDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.UUID;

@RestController
@RequestMapping("/user/seller")
public class SellerUserController {

    @PostMapping("/register")
    public ResponseEntity registerProduct(@RequestBody RequestPRODUCTDTO requestPRODUCTDTO){
        // SellerID -> With the help of sellerId i can get the user object
        // call DB API to get user object
        UUID sellerId = requestPRODUCTDTO.getSellerId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(headers);
        //URL url = new URL("http://localhost:8083/db/user/getuser");

        String urlDB = "http://localhost:8083/db/user/getuser?id=" + sellerId;
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity<UserSignUpDTO> respUser = restTemplate.exchange(urlDB, HttpMethod.GET, httpEntity, UserSignUpDTO.class);

        // database api for acl validation check

        String urlAcl = "http://localhost:8083/db/acl/validate?"+"requestor="+respUser.getBody().getType()+"&"+"operation="+"ADDPRODUCT";
        ResponseEntity<ACLConfigDTO> aclResp = restTemplate.exchange(urlAcl, HttpMethod.GET, httpEntity, ACLConfigDTO.class);

        if(aclResp.getBody().isValidConfig() == false){
          return new ResponseEntity(new GeneralMessageDTO(String.format("User with id %id does not have access to perform this operation", sellerId.toString())), HttpStatus.UNAUTHORIZED);
        }

        String sellerUserURL = "http://localhost:8084/user/seller/product/register";

        HttpEntity httpEntity1 = new HttpEntity(requestPRODUCTDTO, headers);

        ResponseEntity<SellerProductRegistrationDTO> sellerResp = restTemplate.exchange(sellerUserURL, HttpMethod.POST, httpEntity1, SellerProductRegistrationDTO.class);

        return sellerResp;

    }
}
