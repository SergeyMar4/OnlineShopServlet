package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Product;
import com.sergeymar4.onlineshopservlet.repositories.ManufacturerRepository;
import com.sergeymar4.onlineshopservlet.repositories.ProductRepository;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private ProductRepository productRepository;
    private ManufacturerRepository manufacturerRepository;
    private Scanner scanner;

    public ProductController(Scanner scanner) {
        this.productRepository = new ProductRepository();
        this.manufacturerRepository = new ManufacturerRepository();
        this.scanner = scanner;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Product getById(int id) {
        return productRepository.getById(id);
    }

    public void create(String title, int price, int manufacturer_id) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setManufacturer(manufacturerRepository.getById(manufacturer_id));
        productRepository.save(product);
    }

    public void update(int id, String title, int price, int manufacturer_id) {
        Product product = productRepository.getById(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setManufacturer(manufacturerRepository.getById(manufacturer_id));
        productRepository.update(product);
    }

    public void delete(int id) {
        productRepository.delete(productRepository.getById(id));
    }
}
