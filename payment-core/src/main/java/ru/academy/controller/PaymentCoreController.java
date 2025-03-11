package ru.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.academy.dto.PaymentDetailsDto;
import ru.academy.dto.ProductDto;
import ru.academy.service.PaymentCoreService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
@RestController
public class PaymentCoreController {

    private final PaymentCoreService paymentCoreService;

    @GetMapping("/user-products")
    public List<ProductDto> getUserProducts(@RequestParam Long userId) {
        return paymentCoreService.getUserProductsById(userId);
    }

    @PostMapping("/execute")
    public ProductDto executePayment(@RequestBody PaymentDetailsDto paymentDetails) {
        return paymentCoreService.executePayment(paymentDetails);
    }

}
