package ru.academy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.academy.domain.Product;
import ru.academy.domain.User;
import ru.academy.dto.PaymentDetailsDto;
import ru.academy.dto.ProductDto;
import ru.academy.exception.NotEnoughFundsException;
import ru.academy.exception.ProductNotFoundException;
import ru.academy.mapper.ProductMapper;
import ru.academy.repository.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findByUserId(Long userId) {
        User user = userService.findById(userId);
        return productMapper.mapToDtos(productRepository.findByUserId(user.getId()));
    }

    @Transactional
    public ProductDto executePayment(PaymentDetailsDto paymentDetails) {
        User user = userService.findById(paymentDetails.getUserId());
        Product product = findProduct(user, paymentDetails.getProductId());
        double resultBalance = product.getBalance() - paymentDetails.getPaymentAmount();
        if (resultBalance < 0) {
            throw new NotEnoughFundsException("There are not enough funds to process the payment");
        }
        product.setBalance(resultBalance);
        productRepository.save(product);

        return productMapper.mapToDto(product);
    }

    private Product findProduct(User user, Long productId) {
        return user.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(String.format("User with id %d has no product with id %d", user.getId(), productId)));
    }
}
