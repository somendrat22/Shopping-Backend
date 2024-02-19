package com.shopping.backend.user.User.API.Service;

import com.shopping.backend.user.User.API.DTO.GeneralUseDTO.UserDTO;
import com.shopping.backend.user.User.API.DTO.RequestBody.AddProductMailReqBody;
import com.shopping.backend.user.User.API.DTO.RequestBody.SellerProductRegistrationDTO;
import com.shopping.backend.user.User.API.DTO.ResponseBody.MailResponseDto;
import com.shopping.backend.user.User.API.Exceptions.MailNotSendException;
import com.shopping.backend.user.User.API.Exceptions.UnAuthorized;
import com.shopping.backend.user.User.API.Exceptions.UserDoesNotExistException;
import com.shopping.backend.user.User.API.utils.ApiUrlUtil;
import com.shopping.backend.user.User.API.utils.ShoppingLogger;
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
        ShoppingLogger.logger.info("Calling DB API to get user details");
        ResponseEntity<UserDTO> userResp = restTemplate.exchange(url, HttpMethod.GET, httpEntity, UserDTO.class);

        if(userResp.getBody() == null){
            throw new UserDoesNotExistException(String.format("User with id %s does not exist in system.", sellerID.toString()));
        }
        if(!userResp.getBody().getType().equals("SELLER")){
            throw new UnAuthorized(String.format("User with id %s does not have access of this operation", sellerID.toString()));
        }

        // Database API -> to save the prodcut


        ShoppingLogger.logger.info("Calling db api to add product into the database");
        String urlProduct = "http://localhost:8083/db/product/add";

        HttpEntity httpEntityP = new HttpEntity(sellerProductRegistrationDTO, httpHeaders);

        ResponseEntity<SellerProductRegistrationDTO> respProduct = restTemplate.exchange(urlProduct, HttpMethod.POST, httpEntityP, SellerProductRegistrationDTO.class);

        if(respProduct.getBody() == null){
            throw new RuntimeException(String.format("Product addition was not successfully !!"));
        }

        // Mail Service
        ShoppingLogger.logger.info("Calling Mail API to notify seller that product got added");
        String mailUrl = ApiUrlUtil.mailApiURL + "/seller/addproduct";

        AddProductMailReqBody addProductMailReqBody = new AddProductMailReqBody(userResp.getBody().getEmail(), "Hi, How are you ?", "Hii Subject", userResp.getBody().getName());

        HttpEntity mailEntity = new HttpEntity(addProductMailReqBody, httpHeaders);
        ResponseEntity<MailResponseDto> mailResp = restTemplate.exchange(mailUrl, HttpMethod.POST, mailEntity, MailResponseDto.class);
        ShoppingLogger.logger.info("Mail Response : " + mailResp.getBody().toString());
        if(!mailResp.getBody().isSuccess()){
            throw new MailNotSendException(String.format("Mail is not sent to the user with id %s", userResp.getBody().getId()));
        }
        ShoppingLogger.logger.info("register method ended successfully");
        return respProduct.getBody();
    }
}
