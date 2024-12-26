package com.example.mscustomer.controller;

import com.example.mscustomer.model.dto.UpdateBalanceDTO;
import com.example.mscustomer.model.entities.RegisteredCustomer;
import com.example.mscustomer.service.RegisteredCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegisteredCustomerController {

    @Autowired
    private RegisteredCustomerService registeredCustomerService;

    @PostMapping("/{customerId}/updateBalance")
    public ResponseEntity<Void> updateCustomerBalance(@PathVariable Long customerId, @RequestBody UpdateBalanceDTO updateBalanceDTO) {
        registeredCustomerService.updateCustomerBalanceWithCard(customerId, updateBalanceDTO.getNewBalance(), updateBalanceDTO.getCardNumber(), updateBalanceDTO.getOperation());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{customerId}/flightPayment")
    public ResponseEntity<Void> flightPaymentBalance(@PathVariable Long customerId, @RequestBody UpdateBalanceDTO updateBalanceDTO) {
        registeredCustomerService.updateCustomerBalance(customerId, updateBalanceDTO.getNewBalance(), updateBalanceDTO.getOperation());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisteredCustomer> getCustomerById(@PathVariable Long id) {
        var customer = registeredCustomerService.getById(id);
        return ResponseEntity.ok(customer);
    }
}
