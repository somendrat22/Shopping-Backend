package com.shopping.database.api.Database.API.Controller;

import com.shopping.database.api.Database.API.DTO.ProductReqBody;
import com.shopping.database.api.Database.API.models.PortalUser;
import com.shopping.database.api.Database.API.models.Product;
import com.shopping.database.api.Database.API.repository.PortalUserRepo;
import com.shopping.database.api.Database.API.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/db/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    PortalUserRepo portalUserRepo;
    @PostMapping("/add")
    public ProductReqBody addProduct(@RequestBody ProductReqBody product){
        UUID id = UUID.randomUUID();
        Product p = new Product();
        p.setId(id);
        p.setProductName(product.getProductName());
        p.setProductType(product.getProductType());
        p.setRating(0);
        p.setQunatity(product.getQunatity());
        p.setPrice(product.getPrice());
        PortalUser seller = portalUserRepo.findById(product.getSellerId()).orElse(null);
        p.setSeller(seller);
        productRepo.save(p);
        return product;
    }
}
