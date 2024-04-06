package com.sergeymar4.onlineshopservlet.repositories;

import com.sergeymar4.onlineshopservlet.models.Product;
import com.sergeymar4.onlineshopservlet.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductRepository {
    public List<Product> getAll() {
        List<Product> products = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            products = session.createQuery("from Product").list();
        }

        return products;
    }

    public Product getById(int id) {
        Product product = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            product = session.get(Product.class, id);
        }

        if (product == null) {
            System.out.println("Продукта с таким id нет");
        }

        return product;
    }

    public void save(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }
    }

    public void update(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        }
    }

    public void delete(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        }
    }
}
