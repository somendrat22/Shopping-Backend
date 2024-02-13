package com.shopping.database.api.Database.API.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

// user -> oneuser can do many orders
// order -> many orders belongs to on user
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    int quantity;
    double totalPrice;
    boolean isDelivered;
    @ManyToOne
    PortalUser user;
}
