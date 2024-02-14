package com.shopping.database.api.Database.API.Controller;

import com.shopping.database.api.Database.API.models.PortalUser;
import com.shopping.database.api.Database.API.repository.PortalUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db/user")
public class PortalUserController {

    @Autowired
    PortalUserRepo portalUserRepo;

    @PostMapping("/add")
    public PortalUser addUser(@RequestBody PortalUser portalUser){
        portalUserRepo.save(portalUser);
        return portalUser;
    }
}
