package ru.academy.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.academy.domain.Product;
import ru.academy.service.ProductService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class StartWork  implements CommandLineRunner {

    private final ProductService productService;

    @Override
    public void run(String... args) {
        Product product = productService.findById(1L);
        log.info(product.toString());

        List<Product> products = productService.findByUserId(2L);
        products.forEach(p -> log.info(p.toString()));


    }
}
