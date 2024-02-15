package com.shopping.backend.Front.API.Controller;

import com.shopping.backend.Front.API.DTO.UserSignUpDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class PortalUserController {

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpDTO> signup(@RequestBody UserSignUpDTO userSignUpDTO){
        String url = "http://localhost:8083/db/user/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserSignUpDTO> entity = new HttpEntity<UserSignUpDTO>(userSignUpDTO, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.POST, entity, UserSignUpDTO.class);
    }
}
