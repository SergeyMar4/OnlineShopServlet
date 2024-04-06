package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Basket;
import com.sergeymar4.onlineshopservlet.repositories.BasketRepository;
import com.sergeymar4.onlineshopservlet.repositories.CustomerRepository;
import com.sergeymar4.onlineshopservlet.repositories.ProductRepository;
import com.sergeymar4.onlineshopservlet.repositories.ShopRepository;

import java.util.List;
import java.util.Scanner;

public class BasketController {
    private BasketRepository basketRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private ShopRepository shopRepository;
    private Scanner scanner;

    public BasketController(Scanner scanner) {
        this.basketRepository = new BasketRepository();
        this.customerRepository = new CustomerRepository();
        this.productRepository = new ProductRepository();
        this.shopRepository = new ShopRepository();
        this.scanner = scanner;
    }

    public List<Basket> getAll() {
        return basketRepository.getAll();
    }

    public Basket getById(int id) {
        return basketRepository.getById(id);
    }

    public void addProduct(int basket_id, int product_id) {
        basketRepository.addProduct(basketRepository.getById(basket_id), productRepository.getById(product_id));
    }

    public void deleteProduct(int basket_id, int product_id) {
        basketRepository.deleteProduct(basketRepository.getById(basket_id), productRepository.getById(product_id));
    }

    public void create(int customer_id, int shop_id) {
        Basket basket = new Basket();
        basket.setCustomer(customerRepository.getById(customer_id));
        basket.setShop(shopRepository.getById(shop_id));
        basketRepository.save(basket);
    }

    public void update(int id, int customer_id, int shop_id) {
        Basket basket = basketRepository.getById(id);
        basket.setCustomer(customerRepository.getById(customer_id));
        basket.setShop(shopRepository.getById(shop_id));
        basketRepository.update(basket);
    }

    public void delete(int id) {
        basketRepository.delete(basketRepository.getById(id));
    }
}
