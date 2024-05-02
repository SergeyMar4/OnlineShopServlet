package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Basket;
import com.sergeymar4.onlineshopservlet.models.Product;
import com.sergeymar4.onlineshopservlet.repositories.BasketRepository;
import com.sergeymar4.onlineshopservlet.repositories.CustomerRepository;
import com.sergeymar4.onlineshopservlet.repositories.ProductRepository;
import com.sergeymar4.onlineshopservlet.repositories.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class BasketController {
    private BasketRepository basketRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private ShopRepository shopRepository;

    public BasketController() {
        this.basketRepository = new BasketRepository();
        this.customerRepository = new CustomerRepository();
        this.productRepository = new ProductRepository();
        this.shopRepository = new ShopRepository();
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

    public void create(Basket basket) {
        basket.setCustomer(customerRepository.getById(basket.getCustomer().getId()));
        basket.setShop(shopRepository.getById(basket.getShop().getId()));
        basket.setProducts(basket.getProducts());
        basketRepository.save(basket);
    }

    public void update(Basket basket) {
        Basket oldBasket = basketRepository.getById(basket.getId());

        if (basket.getCustomer() != null) {
            oldBasket.setCustomer(customerRepository.getById(basket.getCustomer().getId()));
        }
        if (basket.getShop() != null) {
            oldBasket.setShop(shopRepository.getById(basket.getShop().getId()));
        }
        if (basket.getProducts() != null) {
            List<Product> products = new ArrayList<>();

            for (Product product : basket.getProducts()) {
                products.add(productRepository.getById(product.getId()));
            }

            oldBasket.setProducts(products);
        }

        basketRepository.update(oldBasket);
    }

    public void delete(int id) {
        basketRepository.delete(basketRepository.getById(id));
    }
}
