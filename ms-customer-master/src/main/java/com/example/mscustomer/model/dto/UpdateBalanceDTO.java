package com.example.mscustomer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBalanceDTO {
    private Float newBalance;
    private String operation;
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;
}
