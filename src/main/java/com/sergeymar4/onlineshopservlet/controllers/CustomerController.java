package com.sergeymar4.onlineshopservlet.controllers;

import com.sergeymar4.onlineshopservlet.models.Basket;
import com.sergeymar4.onlineshopservlet.models.Customer;
import com.sergeymar4.onlineshopservlet.repositories.BasketRepository;
import com.sergeymar4.onlineshopservlet.repositories.CustomerRepository;

import java.util.List;

public class CustomerController {
    private CustomerRepository customerRepository;
    private BasketRepository basketRepository;

    public CustomerController() {
        this.customerRepository = new CustomerRepository();
        this.basketRepository = new BasketRepository();
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    public void create(Customer customer) {
        customer.setBaskets(customer.getBaskets());
        customerRepository.save(customer);
    }

    public void update(Customer customer) {
        Customer oldCustomer = customerRepository.getById(customer.getId());

        if (customer.getAge() != 0) {
            oldCustomer.setAge(customer.getAge());
        }
        if (customer.getName() != null) {
            oldCustomer.setName(customer.getName());
        }
        if (customer.getSurname() != null) {
            oldCustomer.setSurname(customer.getSurname());
        }
        if (customer.getMoney() != 0) {
            oldCustomer.setMoney(customer.getMoney());
        }
        if (customer.getBaskets() != null) {
            for (Basket basket : customer.getBaskets()) {
                Basket basket1 = basketRepository.getById(basket.getId());
                basket1.setCustomer(customerRepository.getById(customer.getId()));
                basketRepository.update(basket1);
            }
        }

        customerRepository.update(oldCustomer);
    }

    public void delete(int id) {
        customerRepository.delete(customerRepository.getById(id));
    }
}
