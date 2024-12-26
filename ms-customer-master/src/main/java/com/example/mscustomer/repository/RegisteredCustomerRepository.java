package com.example.mscustomer.repository;

import com.example.mscustomer.model.entities.RegisteredCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredCustomerRepository extends JpaRepository<RegisteredCustomer, Long> {
    boolean existsByUsername(String username);
}
