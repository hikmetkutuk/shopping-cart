package com.shopping.product_service.mapper;

import com.shopping.product_service.dto.ProductRequest;
import com.shopping.product_service.dto.ProductResponse;
import com.shopping.product_service.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest) {
        if (productRequest == null) {
            return null;
        }
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
    }

    public ProductResponse fromProduct(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
