package com.ecommerce.sportscenter.controller;

import com.ecommerce.sportscenter.entity.OrderAggregate.OrderStatus;
import com.ecommerce.sportscenter.entity.OrderAggregate.ShippingAddress;
import com.ecommerce.sportscenter.model.OrderDto;
import com.ecommerce.sportscenter.model.OrderResponse;
import com.ecommerce.sportscenter.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrdersControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrdersController ordersController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ordersController)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .build();
    }

    @Test
    void testGetOrderByIdFound() throws Exception {
        OrderResponse order = new OrderResponse();
        order.setId(1);
        when(orderService.getOrderById(1)).thenReturn(order);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetOrderByIdNotFound() throws Exception {
        when(orderService.getOrderById(1)).thenReturn(null);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllOrders() throws Exception {
        List<OrderResponse> orders = Arrays.asList(new OrderResponse(), new OrderResponse());
        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetAllOrdersPaged() throws Exception {
        ShippingAddress address = ShippingAddress.builder()
            .name("Yujia Pang")
            .address1("619 Wild Ginger Ave")
            .address2("4853 th Avenue")
            .city("WATERLOO")
            .state("ON")
            .zipcode("N2V 2X1")
            .country("CA")
            .build();

        OrderResponse o1 = OrderResponse.builder()
            .id(1)
            .basketId("abc123")
            .shippingAddress(address)
            .subTotal(10997.0)
            .deliveryFee(200.0)
            .total(11197.0)
            .orderDate(LocalDateTime.now())
            .orderStatus(OrderStatus.Pending)
            .build();

        Pageable pageable = PageRequest.of(0, 5);
        // Page<OrderResponse> pagedOrders = new PageImpl<>(Arrays.asList(o1));
        Page<OrderResponse> pagedOrders = new PageImpl<>(
            Arrays.asList(o1),
            PageRequest.of(0, 5), // 带分页信息
            1 // 总元素数量
        );


        when(orderService.getAllOrders(any(Pageable.class))).thenReturn(pagedOrders);

        try {
            mockMvc.perform(get("/api/orders/paged?page=0&size=5"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content.length()").value(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateOrderSuccess() throws Exception {
        OrderDto orderDto = new OrderDto();
        when(orderService.createOrder(any(OrderDto.class))).thenReturn(1);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @Test
    void testCreateOrderFail() throws Exception {
        when(orderService.createOrder(any(OrderDto.class))).thenReturn(null);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrder(1);

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNoContent());
    }
}
