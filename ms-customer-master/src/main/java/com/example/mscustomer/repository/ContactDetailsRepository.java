package com.example.mscustomer.repository;

import com.example.mscustomer.model.entities.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {
}
