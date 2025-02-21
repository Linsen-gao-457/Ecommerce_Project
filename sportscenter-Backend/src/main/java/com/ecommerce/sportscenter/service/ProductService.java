package com.ecommerce.sportscenter.service;

import java.util.List;

import com.ecommerce.sportscenter.model.ProductResponse;

public interface ProductService {
    ProductResponse getProductById(Integer productId);
    List<ProductResponse> getAllProducts();
}
