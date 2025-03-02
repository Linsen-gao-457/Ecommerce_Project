package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Brand;
import com.ecommerce.sportscenter.entity.Product;
import com.ecommerce.sportscenter.entity.Type;
import com.ecommerce.sportscenter.model.ProductResponse;
import com.ecommerce.sportscenter.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("brand1");

        Type type = new Type();
        type.setId(1);
        type.setName("type1");

        sampleProduct = new Product();
        sampleProduct.setId(1);
        sampleProduct.setName("product1");
        sampleProduct.setDescription("product1");
        sampleProduct.setPrice(100);
        sampleProduct.setPictureUrl("image.jpg");
        sampleProduct.setBrand(brand);
        sampleProduct.setType(type);
    }

    @Test
    void testGetProductById_success() {
        when(productRepository.findById(1)).thenReturn(Optional.of(sampleProduct));

        ProductResponse productResponse = productService.getProductById(1);

        assertNotNull(productResponse);
        assertEquals(1, productResponse.getId());
        assertEquals("product1", productResponse.getName());
        assertEquals("brand1", productResponse.getProductBrand());
        assertEquals("type1", productResponse.getProductType());
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    void testGetProductById_notFound() {
        when(productRepository.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> productService.getProductById(2));

        assertEquals("Product doesn't exist", exception.getMessage());
        verify(productRepository, times(1)).findById(2);
    }

    @Test
    void testGetAllBrands() {
        Pageable pageable = mock(Pageable.class);
        Page<Product> mockPage = new PageImpl<>(Collections.singletonList(sampleProduct));

        when(productRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(mockPage);

        Page<ProductResponse> responsePage = productService.getAllProducts(pageable, 1, 1, "product");

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals("product1", responsePage.getContent().get(0).getName());
        verify(productRepository, times(1)).findAll(any(Specification.class), eq(pageable));
    }







}