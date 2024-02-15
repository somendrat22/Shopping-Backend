package com.shopping.database.api.Database.API.Controller;

import com.shopping.database.api.Database.API.DTO.RequestBody.AddAccessDTO;
import com.shopping.database.api.Database.API.DTO.ResponseBody.ACLConfigDTO;
import com.shopping.database.api.Database.API.models.ACL;
import com.shopping.database.api.Database.API.repository.ACLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/db/acl")
public class ACLController {
    @Autowired
    ACLRepository aclRepository;
    @PostMapping("/add")
    public void registerAccess(@RequestBody AddAccessDTO addAccessDTO){
        ACL acl = new ACL();
        acl.setOperation(addAccessDTO.getOperation());
        acl.setRequestor(addAccessDTO.getRequestor());
        aclRepository.save(acl);
    }

    @GetMapping("/validate")
    public ResponseEntity isAccessAvailable(@RequestParam String requestor, @RequestParam String operation){
       ACL acl = aclRepository.getConfiguration(requestor, operation);
       if(acl != null){
           return new ResponseEntity<>(new ACLConfigDTO(true), HttpStatus.OK);
       }else{
           return new ResponseEntity<>(new ACLConfigDTO(false), HttpStatus.OK);
       }
    }

}
