package com.ecommerce.sportscenter.model;

import com.ecommerce.sportscenter.entity.OrderAggregate.ShippingAddress;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDtoTest {

    @Test
    void testOrderDtoGettersAndSetters() {
        OrderDto orderDto = new OrderDto();

        ShippingAddress address = new ShippingAddress();
        address.setCity("Toronto");

        LocalDateTime now = LocalDateTime.now();

        orderDto.setBasketId("123");
        orderDto.setShippingAddress(address);
        orderDto.setSubTotal(100.0);
        orderDto.setDeliveryFee(10.0);
        orderDto.setOrderDate(now);

        assertEquals("123", orderDto.getBasketId());
        assertEquals(address, orderDto.getShippingAddress());
        assertEquals(100.0, orderDto.getSubTotal());
        assertEquals(10.0, orderDto.getDeliveryFee());
        assertEquals(now, orderDto.getOrderDate());
    }

    @Test
    void testOrderDtoConstructor() {
        ShippingAddress address = new ShippingAddress();
        address.setCity("Montreal");

        LocalDateTime orderDate = LocalDateTime.of(2023, 5, 10, 14, 30);

        OrderDto orderDto = new OrderDto("456", address, 150.0, 20.0, orderDate);

        assertEquals("456", orderDto.getBasketId());
        assertEquals(address, orderDto.getShippingAddress());
        assertEquals(150.0, orderDto.getSubTotal());
        assertEquals(20.0, orderDto.getDeliveryFee());
        assertEquals(orderDate, orderDto.getOrderDate());
    }

    @Test
    void testOrderDtoBuilder() {
        ShippingAddress address = new ShippingAddress();
        address.setCity("Vancouver");

        LocalDateTime orderDate = LocalDateTime.of(2024, 3, 20, 12, 0);

        OrderDto orderDto = OrderDto.builder()
                .basketId("789")
                .shippingAddress(address)
                .subTotal(200.0)
                .deliveryFee(15.0)
                .orderDate(orderDate)
                .build();

        assertEquals("789", orderDto.getBasketId());
        assertEquals(address, orderDto.getShippingAddress());
        assertEquals(200.0, orderDto.getSubTotal());
        assertEquals(15.0, orderDto.getDeliveryFee());
        assertEquals(orderDate, orderDto.getOrderDate());
    }
}
