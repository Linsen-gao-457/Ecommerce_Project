package com.ecommerce.sportscenter.repository;
import com.ecommerce.sportscenter.entity.OrderAggregate.Order;
import com.ecommerce.sportscenter.entity.OrderAggregate.OrderStatus;

import io.lettuce.core.dynamic.annotation.Param;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer>{
    List<Order> findByBasketId(Integer basketId);
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order> findByOrderDateBetween(LocalDate starDate, LocalDate endDate);
    @Query("SELECT o FROM Order o JOIN o.orderItems oi WHERE oi.itemOrdered.name LIKE %:productName%")
    List<Order> findByProductNameInOrderItems(@Param("productName") String productName);
    @Query("SELECT o FROM Order o WHERE o.shippingAddress.city = :city")
    List<Order> findByShippingAddressCity(@Param("city") String city);    
} 
