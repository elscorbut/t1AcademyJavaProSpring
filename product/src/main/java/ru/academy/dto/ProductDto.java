package ru.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.academy.domain.Type;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String accountNumber;
    private Double balance;
    private Type type;
    private Long userId;
}
