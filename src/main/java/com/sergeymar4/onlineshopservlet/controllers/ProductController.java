package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Product;
import com.sergeymar4.onlineshopservlet.repositories.ManufacturerRepository;
import com.sergeymar4.onlineshopservlet.repositories.ProductRepository;

import java.util.List;

public class ProductController {
    private ProductRepository productRepository;
    private ManufacturerRepository manufacturerRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
        this.manufacturerRepository = new ManufacturerRepository();
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Product getById(int id) {
        return productRepository.getById(id);
    }

    public void create(Product product) {
        product.setManufacturer(manufacturerRepository.getById(product.getManufacturer().getId()));
        productRepository.save(product);
    }

    public void update(Product product) {
        Product oldProduct = productRepository.getById(product.getId());

        if (product.getManufacturer() != null) {
            oldProduct.setManufacturer(manufacturerRepository.getById(product.getManufacturer().getId()));
        }

        productRepository.update(oldProduct);
    }

    public void delete(int id) {
        productRepository.delete(productRepository.getById(id));
    }
}
