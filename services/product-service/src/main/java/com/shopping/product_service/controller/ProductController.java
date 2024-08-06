package com.shopping.product_service.controller;

import com.shopping.product_service.dto.ProductRequest;
import com.shopping.product_service.dto.ProductResponse;
import com.shopping.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Create a new product using the provided product request.
     *
     * @param productRequest the product request containing details of the product
     * @return the response containing details of the created product
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    /**
     * Retrieves a list of all products.
     *
     * @return list of ProductResponse objects
     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
