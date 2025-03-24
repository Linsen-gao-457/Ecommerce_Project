package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.entity.BasketItem;
import com.ecommerce.sportscenter.model.BasketItemResponse;
import com.ecommerce.sportscenter.model.BasketResponse;
import com.ecommerce.sportscenter.repository.BasketRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Override
    public List<BasketResponse> getAllBasket() {
        List<Basket> basketList = (List<Basket>) basketRepository.findAll();
        List<BasketResponse> basketResponses = basketList.stream()
                .map(this::convertToBasketResoponse)
                .collect(Collectors.toList());
        return basketResponses;
    }

    private BasketResponse convertToBasketResoponse(Basket basket) {
        if (basket == null) {
            return null;
        }
        List<BasketItemResponse> itemResponses = basket.getItems().stream()
                .map(this::convertToBasketItemResponse)
                .collect(Collectors.toList());
        return BasketResponse.builder()
                .id(basket.getId())
                .items(itemResponses)
                .build();
    }

    private BasketItemResponse convertToBasketItemResponse(BasketItem item) {
        return BasketItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .pictureUrl(item.getPictureUrl())
                .productBrand(item.getProductBrand())
                .productType(item.getProductType())
                .quantity(item.getQuantity())
                .build();
    }

    @Override
    public BasketResponse getBasketById(Integer id) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
        if (basketOptional.isPresent()) {
            Basket basket = basketOptional.get();
            return convertToBasketResoponse(basket);
        }
        return null;
    }

    @Override
    public void deleteBasket(Integer id) {
        basketRepository.deleteById(id);

    }

    @Override
    public BasketResponse addBasket(Basket basket) {
        Basket savedBasket = basketRepository.save(basket);
        return convertToBasketResoponse(savedBasket);
    }
}
