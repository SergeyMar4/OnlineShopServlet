package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Customer;
import com.sergeymar4.onlineshopservlet.repositories.CustomerRepository;

import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private CustomerRepository customerRepository;
    private Scanner scanner;

    public CustomerController(Scanner scanner) {
        this.customerRepository = new CustomerRepository();
        this.scanner = scanner;
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    public void create(String name, String surname, int age,int money) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setAge(age);
        customer.setMoney(money);
        customerRepository.save(customer);
    }

    public void update(int id, String name, String surname, int age, int money) {
        Customer customer = customerRepository.getById(id);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setAge(age);
        customer.setMoney(money);
        customerRepository.update(customer);
    }

    public void delete(int id) {
        customerRepository.delete(customerRepository.getById(id));
    }
}
