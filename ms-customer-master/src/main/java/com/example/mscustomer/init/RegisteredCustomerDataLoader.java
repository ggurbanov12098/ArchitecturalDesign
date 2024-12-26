package com.example.mscustomer.init;

import com.example.mscustomer.model.entities.Card;
import com.example.mscustomer.model.entities.ContactDetails;
import com.example.mscustomer.model.entities.RegisteredCustomer;
import com.example.mscustomer.repository.CardRepository;
import com.example.mscustomer.repository.ContactDetailsRepository;
import com.example.mscustomer.repository.RegisteredCustomerRepository;
import com.example.mscustomer.service.impl.RegisteredCustomerServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RegisteredCustomerDataLoader implements CommandLineRunner {
    private final RegisteredCustomerRepository registeredCustomerRepository;
    private final ContactDetailsRepository contactDetailsRepository;
    private final CardRepository cardRepository;

    public RegisteredCustomerDataLoader(RegisteredCustomerRepository registeredCustomerRepository,
                                        ContactDetailsRepository contactDetailsRepository,
                                        CardRepository cardRepository) {
        this.registeredCustomerRepository = registeredCustomerRepository;
        this.contactDetailsRepository = contactDetailsRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        String username = "johndoe"; // Example username
        if (!registeredCustomerRepository.existsByUsername(username)) {
            ContactDetails contact = ContactDetails.builder()
                    .email("john.doe@example.com")
                    .phone("1234567890")
                    .firstName("John")
                    .lastName("Doe")
                    .address("1234 Elm Street, Springfield, Anywhere USA")
                    .build();
            contact = contactDetailsRepository.save(contact);

            // Create and save registered customer
            RegisteredCustomer customer = RegisteredCustomer.builder()
                    .username("johndoe")
                    .password("1234")
                    .accountBalance(1000f)
                    .contactDetails(contact)
                    .build();
            customer = registeredCustomerRepository.save(customer);

            Card card = Card.builder()
                    .customer(customer)
                    .cardNumber("4098362213562563")
                    .cardHolderName("John Doe")
                    .expirationDate("03/2025")
                    .cvv("325")
                    .isValid(true)
                    .build();

            cardRepository.save(card);
        }
    }
}