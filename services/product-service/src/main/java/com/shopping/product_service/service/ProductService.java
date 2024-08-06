package com.shopping.product_service.service;

import com.shopping.product_service.dto.ProductRequest;
import com.shopping.product_service.dto.ProductResponse;
import com.shopping.product_service.exception.ProductCreationException;
import com.shopping.product_service.mapper.ProductMapper;
import com.shopping.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        try {
            var newProduct = productRepository.save(mapper.toProduct(productRequest));
            log.info("Product created successfully with name: {}", productRequest.name());
            return mapper.fromProduct(newProduct);
        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation occurred while creating customer: " + e.getMessage());
            throw new ProductCreationException("Data integrity violation occurred while creating product: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred while creating customer: " + e.getMessage());
            throw new ProductCreationException("Unexpected error occurred while creating product: " + e.getMessage());
        }
    }

    public List<ProductResponse> getAllProducts() {
        try {
            var products = productRepository.findAll()
                    .stream()
                    .map(mapper::fromProduct)
                    .collect(Collectors.toList());
            log.info("Getting all products");
            return products;
        } catch (Exception e) {
            log.error("Unexpected error occurred while getting all products: " + e.getMessage());
            throw new RuntimeException("Unexpected error occurred while getting all products: " + e.getMessage());
        }
    }
}
