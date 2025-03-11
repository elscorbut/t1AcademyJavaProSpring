package ru.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String accountNumber;
    private Double balance;
    private Type type;
    private Long userId;
}
