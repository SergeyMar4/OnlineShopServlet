package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Shop;
import com.sergeymar4.onlineshopservlet.repositories.ShopRepository;

import java.util.List;
import java.util.Scanner;

public class ShopController {
    private ShopRepository shopRepository;
    private Scanner scanner;

    public ShopController(Scanner scanner) {
        this.shopRepository = new ShopRepository();
        this.scanner = scanner;
    }

    public List<Shop> getAll() {
        return shopRepository.getAll();
    }

    public Shop getById(int id) {
        return shopRepository.getById(id);
    }

    public void create(String title) {
        Shop shop = new Shop();
        shop.setTitle(title);
        shopRepository.save(shop);
    }

    public void update(int id, String title) {
        Shop shop = shopRepository.getById(id);
        shop.setTitle(title);
        shopRepository.update(shop);
    }

    public void delete(int id) {
        shopRepository.delete(shopRepository.getById(id));
    }
}
