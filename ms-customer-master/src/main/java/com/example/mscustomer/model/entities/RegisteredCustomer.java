package com.example.mscustomer.model.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registered-customer")
public class RegisteredCustomer extends Customer {
    private String username;
    private String password;
    private Float accountBalance = 0f;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactdetails_id")
    private ContactDetails contactDetails;
}
