package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Manufacturer;
import com.sergeymar4.onlineshopservlet.repositories.ManufacturerRepository;

import java.util.List;
import java.util.Scanner;

public class ManufacturerController {
    private ManufacturerRepository manufacturerRepository;
    private Scanner scanner;

    public ManufacturerController(Scanner scanner) {
        this.manufacturerRepository = new ManufacturerRepository();
        this.scanner = scanner;
    }

    public List<Manufacturer> getAll() {
        return manufacturerRepository.getAll();
    }

    public Manufacturer getById(int id) {
        return manufacturerRepository.getById(id);
    }

    public void create(String title, String country) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setTitle(title);
        manufacturer.setCountry(country);
        manufacturerRepository.save(manufacturer);
    }

    public void update(int id, String title, String country) {
        Manufacturer manufacturer = manufacturerRepository.getById(id);
        manufacturer.setTitle(title);
        manufacturer.setCountry(country);
        manufacturerRepository.update(manufacturer);
    }

    public void delete(int id) {
        manufacturerRepository.delete(manufacturerRepository.getById(id));
    }
}
