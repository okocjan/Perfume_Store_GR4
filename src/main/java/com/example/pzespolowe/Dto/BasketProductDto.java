package com.example.pzespolowe.Dto;

import com.example.pzespolowe.Models.Projection.BasketProjection;

import java.util.ArrayList;
import java.util.List;

public class BasketProductDto {
    private List<BasketProjection> basketItems;
    private Double finalPrice;

    public BasketProductDto() {
        basketItems = new ArrayList<>();
        finalPrice = (double) 0;
    }

    public List<BasketProjection> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketProjection> basketItems) {
        this.basketItems = basketItems;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public String toString() {
        return "BasketProductDto{" +
                "basketItems=" + basketItems +
                ", finalPrice=" + finalPrice +
                '}';
    }
}
