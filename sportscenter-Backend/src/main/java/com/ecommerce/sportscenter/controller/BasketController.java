package com.ecommerce.sportscenter.controller;

import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.entity.BasketItem;
import com.ecommerce.sportscenter.model.BasketItemResponse;
import com.ecommerce.sportscenter.model.BasketResponse;
import com.ecommerce.sportscenter.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping
    public List<BasketResponse> getAllBaskets() {
        return basketService.getAllBasket();
    }

    @GetMapping("/{basketId}")
    public BasketResponse getBasket(@PathVariable String basketId) {
        Integer id = Integer.parseInt(basketId);
        System.out.println(basketService.getBasketById(id));
        return basketService.getBasketById(id);
    }

    @DeleteMapping("/{basketId}")
    public void deleteBasket(@PathVariable String basketId) {
        Integer id = Integer.parseInt(basketId);
        basketService.deleteBasketById(id);
    }

    @PostMapping
    public ResponseEntity<BasketResponse> createBasket(@RequestBody BasketResponse basketResponse) {
        Basket basket = convertToBasketEntity(basketResponse);
        BasketResponse createdBasket = basketService.addBasket(basket);
        return new ResponseEntity<>(createdBasket, HttpStatus.CREATED);
    }

    private Basket convertToBasketEntity(BasketResponse basketResponse) {
        Basket basket = new Basket();
        basket.setId(basketResponse.getId());
        basket.setItems(mapBasketItemResponsesItemsToEntities(basketResponse.getItems()));
        return basket;
    }

    private List<BasketItem> mapBasketItemResponsesItemsToEntities(List<BasketItemResponse> itemResponses) {
        return itemResponses.stream()
                .map(this::convertToBasketItemEntity)
                .collect(Collectors.toList());
    }

    private BasketItem convertToBasketItemEntity(BasketItemResponse basketItemResponse) {
        BasketItem basketItem = new BasketItem();
        basketItem.setId(basketItemResponse.getId());
        basketItem.setName(basketItemResponse.getName());
        basketItem.setDescription(basketItemResponse.getDescription());
        basketItem.setPrice(basketItemResponse.getPrice());
        basketItem.setPictureUrl(basketItemResponse.getPictureUrl());
        basketItem.setProductBrand(basketItemResponse.getProductBrand());
        basketItem.setQuantity(basketItemResponse.getQuantity());
        basketItem.setProductType(basketItemResponse.getProductType());
        return basketItem;
    }
}
