package com.shopping.database.api.Database.API.Controller;

import com.shopping.database.api.Database.API.DTO.RequestBody.AddAccessDTO;
import com.shopping.database.api.Database.API.models.ACL;
import com.shopping.database.api.Database.API.repository.ACLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
