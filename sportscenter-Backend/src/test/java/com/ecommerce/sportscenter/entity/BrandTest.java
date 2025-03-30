package com.ecommerce.sportscenter.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    private Brand brand;

    @BeforeEach
    void setUp() {
        // Setup a new Brand object before each test
        brand = new Brand();
    }

    @Test
    void testBrandConstructor() {
        // Test the default constructor
        assertNotNull(brand, "The brand should not be null after creation");
        assertNull(brand.getId(), "The ID should be null initially");
        assertNull(brand.getName(), "The name should be null initially");
        // assertNotNull(brand.getProducts(), "The product list should be initialized");
        // assertTrue(brand.getProducts().isEmpty(), "The product list should be empty initially");
    }

    @Test
    void testBrandSetterAndGetter() {
        // Set values using setters
        brand.setId(1);
        brand.setName("Nike");

        // Create some Product objects (as dummy data)
        Product product1 = Product.builder().id(1).name("Product1").brand(brand).build();
        Product product2 = Product.builder().id(2).name("Product2").brand(brand).build();

        // Set products list
        brand.setProducts(Arrays.asList(product1, product2));

        // Assert that the values are set correctly using getters
        assertEquals(1, brand.getId(), "The ID should be 1");
        assertEquals("Nike", brand.getName(), "The name should be 'Nike'");
        assertEquals(2, brand.getProducts().size(), "The brand should have 2 products");

        // Test product relationships
        assertEquals("Product1", brand.getProducts().get(0).getName(), "The first product should be 'Product1'");
        assertEquals("Product2", brand.getProducts().get(1).getName(), "The second product should be 'Product2'");
    }

    @Test
    void testBrandIdSetterAndGetter() {
        // Set the ID using the setter
        brand.setId(5);

        // Assert that the ID is correctly set
        assertEquals(5, brand.getId(), "The ID should be 5");
    }

    @Test
    void testBrandNameSetterAndGetter() {
        // Set the name using the setter
        brand.setName("Adidas");

        // Assert that the name is correctly set
        assertEquals("Adidas", brand.getName(), "The name should be 'Adidas'");
    }

    @Test
    void testBrandWithProducts() {
        // Create some products
        Product product1 = Product.builder().id(1).name("Product A").brand(brand).build();
        Product product2 = Product.builder().id(2).name("Product B").brand(brand).build();

        // Add products to the brand
        brand.setProducts(Arrays.asList(product1, product2));

        // Verify products are correctly assigned
        assertNotNull(brand.getProducts(), "The products list should not be null");
        assertEquals(2, brand.getProducts().size(), "The brand should have 2 products");
        assertTrue(brand.getProducts().contains(product1), "The product list should contain 'Product A'");
        assertTrue(brand.getProducts().contains(product2), "The product list should contain 'Product B'");
    }

    @Test
    void testBrandProductRelationship() {
        // Create a product
        Product product = Product.builder().id(1).name("Product A").brand(brand).build();

        // Set the brand for the product
        product.setBrand(brand);

        // Assert that the brand of the product is correctly set
        assertEquals(brand, product.getBrand(), "The brand of the product should be the same as the brand object");
    }
}
