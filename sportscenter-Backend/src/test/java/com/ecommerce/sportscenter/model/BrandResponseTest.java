package com.ecommerce.sportscenter.model;

import com.ecommerce.sportscenter.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BrandResponseTest {

    @Test
    void testBrandResponseGettersAndSetters() {
        // Create a new BrandResponse instance using the default constructor
        BrandResponse brandResponse = new BrandResponse();

        // Test setter and getter for id
        brandResponse.setId(1);
        assertEquals(1, brandResponse.getId(), "The brand ID should be 1");

        // Test setter and getter for name
        brandResponse.setName("Nike");
        assertEquals("Nike", brandResponse.getName(), "The brand name should be 'Nike'");

        // Test setter and getter for products (list of Product entities)
        Product product = new Product(); // Create a mock Product entity
        product.setId(101);
        product.setName("Football");
        brandResponse.setProducts(Arrays.asList(product));
        assertEquals(1, brandResponse.getProducts().size(), "The brand should have 1 product");
        assertEquals("Football", brandResponse.getProducts().get(0).getName(), "The product name should be 'Football'");
    }

    @Test
    void testBrandResponseConstructor() {
        // Create a new BrandResponse instance using the all-args constructor
        Product product = new Product();
        product.setId(101);
        product.setName("Football");

        BrandResponse brandResponse = new BrandResponse(1, "Nike", Arrays.asList(product));

        // Test constructor assignment
        assertEquals(1, brandResponse.getId(), "The brand ID should be 1");
        assertEquals("Nike", brandResponse.getName(), "The brand name should be 'Nike'");
        assertEquals(1, brandResponse.getProducts().size(), "The brand should have 1 product");
        assertEquals("Football", brandResponse.getProducts().get(0).getName(), "The product name should be 'Football'");
    }

    @Test
    void testBrandResponseBuilder() {
        // Create a new BrandResponse using the builder
        Product product = new Product();
        product.setId(101);
        product.setName("Football");

        BrandResponse brandResponse = BrandResponse.builder()
                .id(1)
                .name("Nike")
                .products(Arrays.asList(product))
                .build();

        // Test builder assignment
        assertEquals(1, brandResponse.getId(), "The brand ID should be 1");
        assertEquals("Nike", brandResponse.getName(), "The brand name should be 'Nike'");
        assertEquals(1, brandResponse.getProducts().size(), "The brand should have 1 product");
        assertEquals("Football", brandResponse.getProducts().get(0).getName(), "The product name should be 'Football'");
    }
}
