package ru.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.academy.dto.PaymentDetailsDto;
import ru.academy.dto.ProductDto;
import ru.academy.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/user")
    public List<ProductDto> getProducts(@RequestParam Long userId) {
        return productService.findByUserId(userId);
    }

    @PostMapping("/payment")
    public ProductDto executePayment(@RequestBody PaymentDetailsDto paymentDetails) {
        return productService.executePayment(paymentDetails);
    }
}
