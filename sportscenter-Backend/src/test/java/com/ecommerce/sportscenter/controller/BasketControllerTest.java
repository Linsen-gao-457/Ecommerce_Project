package com.ecommerce.sportscenter.controller;

import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.model.BasketResponse;
import com.ecommerce.sportscenter.service.BasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BasketControllerTest {

    @Mock
    private BasketService basketService;

    @InjectMocks
    private BasketController basketController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(basketController).build();
    }

    @Test
    void testGetAllBaskets() throws Exception {
        List<BasketResponse> baskets = Arrays.asList(new BasketResponse("1", new ArrayList<>()), new BasketResponse("2", new ArrayList<>()));
        when(basketService.getAllBasket()).thenReturn(baskets);

        mockMvc.perform(get("/api/baskets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testGetBasketById() throws Exception {
        BasketResponse basketResponse = new BasketResponse("1", new ArrayList<>());
        when(basketService.getBasketById("1")).thenReturn(basketResponse);

        mockMvc.perform(get("/api/baskets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetBasketById_NotFound() throws Exception {
        when(basketService.getBasketById("1")).thenReturn(null);

        mockMvc.perform(get("/api/baskets/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateBasket() throws Exception {
        BasketResponse basketResponse = new BasketResponse("1", new ArrayList<>());
        Basket basket = new Basket();
        when(basketService.addBasket(any(Basket.class))).thenReturn(basketResponse);

        mockMvc.perform(post("/api/baskets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1, \"items\": []}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDeleteBasket() throws Exception {
        doNothing().when(basketService).deleteBasketById("1");

        mockMvc.perform(delete("/api/baskets/1"))
                .andExpect(status().isOk());
    }
}

