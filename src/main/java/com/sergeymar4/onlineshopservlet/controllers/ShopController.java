package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Shop;
import com.sergeymar4.onlineshopservlet.repositories.ShopRepository;

import java.util.List;

public class ShopController {
    private ShopRepository shopRepository;

    public ShopController() {
        this.shopRepository = new ShopRepository();
    }

    public List<Shop> getAll() {
        return shopRepository.getAll();
    }

    public Shop getById(int id) {
        return shopRepository.getById(id);
    }

    public void create(Shop shop) {
        shopRepository.save(shop);
    }

    public void update(Shop shop) {
        shopRepository.update(shop);
    }

    public void delete(int id) {
        shopRepository.delete(shopRepository.getById(id));
    }
}
