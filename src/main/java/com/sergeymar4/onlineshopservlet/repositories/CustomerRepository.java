package com.sergeymar4.onlineshopservlet.repositories;

import com.sergeymar4.onlineshopservlet.models.Customer;
import com.sergeymar4.onlineshopservlet.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerRepository {
    public List<Customer> getAll() {
        List<Customer> customers = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            customers = session.createQuery("from Customer").list();
        }

        return customers;
    }

    public Customer getById(int id) {
        Customer customer = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            customer = session.get(Customer.class, id);
        }

        if (customer == null) {
            System.out.println("Покупателя с таким id нет");
        }

        return customer;
    }

    public void save(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }
    }

    public void update(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        }
    }

    public void delete(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
        }
    }
}
