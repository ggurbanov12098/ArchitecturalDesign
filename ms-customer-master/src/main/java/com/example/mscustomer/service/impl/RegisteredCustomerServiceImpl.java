package com.example.mscustomer.service.impl;

import com.example.mscustomer.model.entities.Card;
import com.example.mscustomer.model.entities.RegisteredCustomer;
import com.example.mscustomer.repository.CardRepository;
import com.example.mscustomer.repository.RegisteredCustomerRepository;
import com.example.mscustomer.service.RegisteredCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class RegisteredCustomerServiceImpl implements RegisteredCustomerService {
    private final RegisteredCustomerRepository registeredCustomerRepository;
    private final CardRepository cardRepository;

    RegisteredCustomerServiceImpl(RegisteredCustomerRepository registeredCustomerRepository, CardRepository cardRepository) {
        this.registeredCustomerRepository = registeredCustomerRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public RegisteredCustomer getById(Long id) {
        return registeredCustomerRepository.findById(id).get();
    }

    @Override
    public void updateCustomerBalanceWithCard(Long customerId, Float newBalance, String cardNumber, String operation) {

        Card card = updateCardValidity(cardNumber);

        if (!card.getIsValid() || !card.getCustomer().getId().equals(customerId)) {
            throw new IllegalStateException("Card is invalid or does not belong to the customer");
        }

        updateCustomerBalance(customerId, newBalance, operation);
    }

    @Transactional
    public Card updateCardValidity(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new IllegalArgumentException("Invalid card number"));

        boolean isExpired = card.checkIfCardIsExpired();
        card.setIsValid(!isExpired);
        return cardRepository.save(card);
    }

    @Override
    public void updateCustomerBalance(Long customerId, Float newBalance, String operation) {
        RegisteredCustomer customer = registeredCustomerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        if(Objects.equals(operation, "+")) customer.setAccountBalance(customer.getAccountBalance() + newBalance);
        else if(Objects.equals(operation, "-")) customer.setAccountBalance(customer.getAccountBalance() - newBalance);
        registeredCustomerRepository.save(customer);
    }
}
