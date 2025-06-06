package com.ecommerce.clothesstore.controller;

import com.ecommerce.clothesstore.entity.Basket;
import com.ecommerce.clothesstore.entity.BasketItem;
import com.ecommerce.clothesstore.model.BasketItemResponse;
import com.ecommerce.clothesstore.model.BasketResponse;
import com.ecommerce.clothesstore.service.BasketService;
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
    public List<BasketResponse> getAllBaskets()
    {
        return basketService.getAllBaskets();
    }

    @GetMapping("/{basketId}")
    public BasketResponse getBasketById(@PathVariable String basketId)
    {
        return basketService.getBasketById(basketId);
    }

    @DeleteMapping("/{basketId}")
    public void deleteBasketById(@PathVariable String basketid)
    {
        basketService.deleteBasketById(basketid);
    }

    @PostMapping
    public ResponseEntity<BasketResponse> createBasket(@RequestBody BasketResponse basketResponse)
    {
        //convert basketResponse to basket entity
        Basket basket=convertToBasketEntity(basketResponse);
        BasketResponse createdBasket=basketService.createBasket(basket);
        return new ResponseEntity<>(createdBasket, HttpStatus.CREATED);
    }

    private Basket convertToBasketEntity(BasketResponse basketResponse) {
        Basket basket=new Basket();
        basket.setId(basketResponse.getId());
        basket.setItems(mapBasketItemResponsesToEntities(basketResponse.getItems()));
        return basket;
    }

    private List<BasketItem> mapBasketItemResponsesToEntities(List<BasketItemResponse> items) {
        return items.stream().map(this::convertToBasketItemEntity).collect(Collectors.toList());
    }

    private BasketItem convertToBasketItemEntity(BasketItemResponse basketItemResponse) {
        BasketItem basketItem=new BasketItem();
        basketItem.setId(basketItemResponse.getId());
        basketItem.setName(basketItemResponse.getName());
        basketItem.setDescription(basketItemResponse.getDescription());
        basketItem.setPrice(basketItemResponse.getPrice());
        basketItem.setPictureUrl(basketItemResponse.getPictureUrl());
        basketItem.setProductBrand(basketItemResponse.getProductBrand());
        basketItem.setProductType(basketItemResponse.getProductType());
        basketItem.setQuantity(basketItemResponse.getQuantity());

        return basketItem;
    }
}
