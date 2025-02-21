package com.ecommerce.sportscenter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.sportscenter.entity.Product;
import com.ecommerce.sportscenter.model.ProductResponse;
import com.ecommerce.sportscenter.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    public final ProductRepository productRepository;
    
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse getProductById(Integer productId) {
        log.info("fetching Product by Id: {}", productId);
        Product product = productRepository.findById(productId)
                            .orElseThrow(()->new RuntimeException("Product doesn't exist"));

        ProductResponse productResponse = convertToProductResponse(product);
        log.info("Fetched Product by Product Id: {}", productId);
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        log.info("Fetch all products");
        List<ProductResponse> productResponses = products.stream()
                            .map(this::convertToProductResponse).collect(Collectors.toList());
        log.info("Fetched all products");
        return productResponses; 
    }

    private ProductResponse convertToProductResponse(Product product){
        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .pictureUrl(product.getPictureUrl())
            .productBrand(product.getBrand().getName())
            .productType(product.getType().getName())
            .build();
    }
}
