package com.ecommerce.sportscenter.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @Mock
    private Brand brand;

    @Mock
    private Type type;

    @BeforeEach
    void setUp() {
        // Setup a new Product object before each test
        product = new Product();
    }

    @Test
    void testProductConstructor() {
        // Test the default constructor
        assertNotNull(product, "The product should not be null after creation");
        assertNull(product.getId(), "The ID should be null initially");
        assertNull(product.getName(), "The name should be null initially");
        assertNull(product.getDescription(), "The description should be null initially");
        assertEquals(0L, product.getPrice(), "The price should be 0 initially");
        assertNull(product.getPictureUrl(), "The picture URL should be null initially");
        assertNull(product.getBrand(), "The brand should be null initially");
        assertNull(product.getType(), "The type should be null initially");
    }

    @Test
    void testProductSetterAndGetter() {
        // Set values using setters
        product.setId(1);
        product.setName("Product1");
        product.setDescription("Product Description");
        product.setPrice(100L);
        product.setPictureUrl("http://example.com/product1.jpg");

        // Create mock Brand and Type objects
        brand = new Brand();
        brand.setId(1);
        brand.setName("Brand1");
        product.setBrand(brand);

        type = new Type();
        type.setId(1);
        type.setName("Type1");
        product.setType(type);

        // Assert that the values are set correctly using getters
        assertEquals(1, product.getId(), "The ID should be 1");
        assertEquals("Product1", product.getName(), "The name should be 'Product1'");
        assertEquals("Product Description", product.getDescription(), "The description should be 'Product Description'");
        assertEquals(100L, product.getPrice(), "The price should be 100");
        assertEquals("http://example.com/product1.jpg", product.getPictureUrl(), "The picture URL should be 'http://example.com/product1.jpg'");
        assertEquals(brand, product.getBrand(), "The brand should be the same as the mock brand");
        assertEquals(type, product.getType(), "The type should be the same as the mock type");
    }

    @Test
    void testProductBrandSetterAndGetter() {
        // Set brand using setter
        product.setBrand(brand);

        // Assert that the brand is correctly set
        assertEquals(brand, product.getBrand(), "The brand should be correctly set");
    }

    @Test
    void testProductTypeSetterAndGetter() {
        // Set type using setter
        product.setType(type);

        // Assert that the type is correctly set
        assertEquals(type, product.getType(), "The type should be correctly set");
    }

    @Test
    void testProductRelationshipWithBrand() {
        // Create and set a brand
        brand = new Brand();
        brand.setId(1);
        brand.setName("Brand1");
        product.setBrand(brand);

        // Assert the product has the correct brand
        assertNotNull(product.getBrand(), "The product should have a brand");
        assertEquals("Brand1", product.getBrand().getName(), "The brand's name should be 'Brand1'");
    }

    @Test
    void testProductRelationshipWithType() {
        // Create and set a type
        type = new Type();
        type.setId(1);
        type.setName("Type1");
        product.setType(type);

        // Assert the product has the correct type
        assertNotNull(product.getType(), "The product should have a type");
        assertEquals("Type1", product.getType().getName(), "The type's name should be 'Type1'");
    }

    @Test
    void testProductBuilder() {
        // Test the builder pattern
        product = Product.builder()
                .id(1)
                .name("Product1")
                .description("Product Description")
                .price(100L)
                .pictureUrl("http://example.com/product1.jpg")
                .brand(brand)
                .type(type)
                .build();

        // Assert that the builder sets the fields correctly
        assertNotNull(product, "The product should not be null after building");
        assertEquals(1, product.getId(), "The ID should be 1");
        assertEquals("Product1", product.getName(), "The name should be 'Product1'");
        assertEquals("Product Description", product.getDescription(), "The description should be 'Product Description'");
        assertEquals(100L, product.getPrice(), "The price should be 100");
        assertEquals("http://example.com/product1.jpg", product.getPictureUrl(), "The picture URL should be 'http://example.com/product1.jpg'");
        assertEquals(brand, product.getBrand(), "The brand should be correctly set using builder");
        assertEquals(type, product.getType(), "The type should be correctly set using builder");
    }
}
