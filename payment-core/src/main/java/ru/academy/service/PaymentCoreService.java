package ru.academy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.academy.dto.PaymentDetailsDto;
import ru.academy.dto.ProductDto;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PaymentCoreService {

    private final RestTemplate restTemplate;

    public List<ProductDto> getUserProductsById(Long userId) {
        ResponseEntity<List<ProductDto>> responseEntity =
                restTemplate.exchange("/api/v1/products/user?userId={userId}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        }, userId);

        return responseEntity.getBody();
    }

    public ProductDto executePayment(PaymentDetailsDto paymentDetails) {
        return restTemplate.postForObject("/api/v1/products/payment",
                paymentDetails, ProductDto.class);
    }
}
