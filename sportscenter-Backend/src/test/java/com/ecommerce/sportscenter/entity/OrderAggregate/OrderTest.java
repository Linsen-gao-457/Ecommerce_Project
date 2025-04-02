package com.ecommerce.sportscenter.entity.OrderAggregate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void testDefaultValues() {
        assertNull(order.getId());
        assertEquals(OrderStatus.Pending, order.getOrderStatus(), "Default status should be Pending");
    }

    @Test
    void testSettersAndGetters() {
        ShippingAddress address = ShippingAddress.builder().city("Toronto").build();
        LocalDateTime now = LocalDateTime.now();

        OrderItem item = new OrderItem();
        item.setId(1);

        order.setId(100);
        order.setBasketId("10");
        order.setShippingAddress(address);
        order.setOrderDate(now);
        order.setOrderItems(List.of(item));
        order.setSubTotal(200.0);
        order.setDeliveryFee(20.0);
        order.setOrderStatus(OrderStatus.PaymentReceived);

        assertEquals(100, order.getId());
        assertEquals("10", order.getBasketId());
        assertEquals(address, order.getShippingAddress());
        assertEquals(now, order.getOrderDate());
        assertEquals(200.0, order.getSubTotal());
        assertEquals(20.0, order.getDeliveryFee());
        assertEquals(220.0, order.getTotal());
        assertEquals(OrderStatus.PaymentReceived, order.getOrderStatus());
        assertEquals(1, order.getOrderItems().size());
    }

    @Test
    void testOrderBuilder() {
        ShippingAddress address = ShippingAddress.builder().city("Calgary").build();
        LocalDateTime now = LocalDateTime.now();

        Order order = Order.builder()
                .id(10)
                .basketId("20")
                .shippingAddress(address)
                .orderDate(now)
                .subTotal(300.0)
                .deliveryFee(30.0)
                .orderStatus(OrderStatus.PaymentFailed)
                .build();

        assertEquals(10, order.getId());
        assertEquals("20", order.getBasketId());
        assertEquals(address, order.getShippingAddress());
        assertEquals(300.0, order.getSubTotal());
        assertEquals(30.0, order.getDeliveryFee());
        assertEquals(OrderStatus.PaymentFailed, order.getOrderStatus());
    }
}
