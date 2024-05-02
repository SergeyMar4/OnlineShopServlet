package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Manufacturer;
import com.sergeymar4.onlineshopservlet.repositories.ManufacturerRepository;

import java.util.List;

public class ManufacturerController {
    private ManufacturerRepository manufacturerRepository;

    public ManufacturerController() {
        this.manufacturerRepository = new ManufacturerRepository();
    }

    public List<Manufacturer> getAll() {
        return manufacturerRepository.getAll();
    }

    public Manufacturer getById(int id) {
        return manufacturerRepository.getById(id);
    }

    public void create(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    public void update(Manufacturer manufacturer) {
        manufacturerRepository.update(manufacturer);
    }

    public void delete(int id) {
        manufacturerRepository.delete(manufacturerRepository.getById(id));
    }
}
