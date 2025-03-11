package ru.academy.mapper;

import org.springframework.stereotype.Component;
import ru.academy.domain.Product;
import ru.academy.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public List<ProductDto> mapToDtos(List<Product> products) {
        return products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductDto mapToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getAccountNumber(),
                product.getBalance(),
                product.getType(),
                product.getUser().getId()
        );
    }
}
