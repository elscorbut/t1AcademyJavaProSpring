package ru.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDetailsDto {

    private Long userId;
    private Long productId;
    private Double paymentAmount;
}
