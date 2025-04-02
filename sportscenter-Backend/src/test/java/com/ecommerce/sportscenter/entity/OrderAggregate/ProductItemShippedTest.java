package com.ecommerce.sportscenter.entity.OrderAggregate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductItemOrderedTest {

    @Test
    void testProductItemOrderedSettersAndGetters() {
        ProductItemOrdered item = new ProductItemOrdered();
        item.setProductId(1);
        item.setName("Football");
        item.setPictureUrl("img.jpg");

        assertEquals(1, item.getProductId());
        assertEquals("Football", item.getName());
        assertEquals("img.jpg", item.getPictureUrl());
    }

    @Test
    void testProductItemOrderedBuilder() {
        ProductItemOrdered item = ProductItemOrdered.builder()
                .productId(2)
                .name("Basketball")
                .pictureUrl("basket.jpg")
                .build();

        assertEquals(2, item.getProductId());
        assertEquals("Basketball", item.getName());
        assertEquals("basket.jpg", item.getPictureUrl());
    }
}
