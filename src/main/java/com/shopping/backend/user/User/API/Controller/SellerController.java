package com.shopping.backend.user.User.API.Controller;

import com.shopping.backend.user.User.API.DTO.GeneralUseDTO.GeneralMessageDTO;
import com.shopping.backend.user.User.API.DTO.RequestBody.SellerProductRegistrationDTO;
import com.shopping.backend.user.User.API.Exceptions.UnAuthorized;
import com.shopping.backend.user.User.API.Exceptions.UserDoesNotExistException;
import com.shopping.backend.user.User.API.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;


    @PostMapping("/product/register")
    public ResponseEntity registerProduct(@RequestBody SellerProductRegistrationDTO sellerProductRegistrationDTO){
        // Database API -> Product will get register in the database;
        // Email API -> Notify Seller hey your product got registered on your portal
        try{
            SellerProductRegistrationDTO resp = sellerService.register(sellerProductRegistrationDTO);
            return new ResponseEntity(resp, HttpStatus.CREATED);
        }catch (UnAuthorized unAuthorized){
            return new ResponseEntity(new GeneralMessageDTO(unAuthorized.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(UserDoesNotExistException userDoesNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(userDoesNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        }catch(RuntimeException runtimeException){
            return new ResponseEntity(new GeneralMessageDTO(runtimeException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
