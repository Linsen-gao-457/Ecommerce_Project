package com.ecommerce.sportscenter.controller;

import com.ecommerce.sportscenter.model.BrandResponse;
import com.ecommerce.sportscenter.model.ProductResponse;
import com.ecommerce.sportscenter.model.TypeResponse;
import com.ecommerce.sportscenter.service.BrandService;
import com.ecommerce.sportscenter.service.ProductService;
import com.ecommerce.sportscenter.service.TypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private BrandService brandService;

    @Mock
    private TypeService typeService;

    @InjectMocks
    private ProductController productController;

    private ProductResponse productResponse1, productResponse2;
    private BrandResponse brandResponse1, brandResponse2;
    private TypeResponse typeResponse1, typeResponse2;

    @BeforeEach
    void setUp() {
        productResponse1 = new ProductResponse(1, "product1", "description1", 100L, "url1", "brand1", "type1");
        productResponse2 = new ProductResponse(2, "product2", "description2", 100L, "url2", "brand2", "type2");

        brandResponse1 = new BrandResponse(1, "brand1", null);
        brandResponse2 = new BrandResponse(2, "brand2", null);

        typeResponse1 = new TypeResponse(1, "type1", null);
        typeResponse2 = new TypeResponse(2, "type2", null);
    }

    @Test
    void testGetProductById() {
        when(productService.getProductById(1)).thenReturn(productResponse1);

        ResponseEntity<ProductResponse> response = productController.getProductById(1);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("product1", response.getBody().getName());

        verify(productService, times(1)).getProductById(1);
    }

    @Test
    void testGetAllProducts() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        Page<ProductResponse> page = new PageImpl<>(Arrays.asList(productResponse1, productResponse2));

        when(productService.getAllProducts(pageable, null, null, null)).thenReturn(page);

        ResponseEntity<Page<ProductResponse>> response = productController.getAllProducts(0, 10, null, null, null, "name", "asc");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().getContent().size());
        assertEquals("product1", response.getBody().getContent().get(0).getName());

        verify(productService, times(1)).getAllProducts(pageable, null, null, null);
    }

    @Test
    void testGetAllBrands() {
        when(brandService.getAllBrands()).thenReturn(Arrays.asList(brandResponse1, brandResponse2));

        ResponseEntity<List<BrandResponse>> response = productController.getAllBrands();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("brand1", response.getBody().get(0).getName());

        verify(brandService, times(1)).getAllBrands();
    }

    @Test
    void testGetAllTypes() {
        when(typeService.getAllTypes()).thenReturn(Arrays.asList(typeResponse1, typeResponse2));

        ResponseEntity<List<TypeResponse>> response = productController.getAllTypes();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("type1", response.getBody().get(0).getName());

        verify(typeService, times(1)).getAllTypes();
    }
}