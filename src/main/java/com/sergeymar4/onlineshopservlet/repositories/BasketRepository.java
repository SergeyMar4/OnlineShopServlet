package com.sergeymar4.onlineshopservlet.repositories;

import com.sergeymar4.onlineshopservlet.models.Basket;
import com.sergeymar4.onlineshopservlet.models.Product;
import com.sergeymar4.onlineshopservlet.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BasketRepository {
    private ProductRepository productRepository;

    public BasketRepository() {
        this.productRepository = new ProductRepository();
    }

    public List<Basket> getAll() {
        List<Basket> baskets = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            baskets = session.createQuery("from Basket").list();
        }

        return baskets;
    }

    public Basket getById(int id) {
        Basket basket = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            basket = session.get(Basket.class, id);
        }

        if (basket == null) {
            System.out.println("Корзины с таким id нет");
        }

        return basket;
    }

    public void addProduct(Basket basket, Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            basket.getProducts().add(product);
            changeMoneyMinus(basket.getId(), product.getId());
            Transaction transaction = session.beginTransaction();
            session.update(basket);
            transaction.commit();
        }
    }

    public void deleteProduct(Basket basket, Product product) {
        List<Product> products = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            for (Product product1 : basket.getProducts()) {
                if (product1.getId() == product.getId()) {
                    products.add(product1);
                }
            }

            for (Product product1 : products) {
                basket.getProducts().remove(product1);
                changeMoneyPlus(basket.getId(), product.getId());
            }

            Transaction transaction = session.beginTransaction();
            session.update(basket);
            transaction.commit();
        }
    }

    public void changeMoneyMinus(int basket_id, int product_id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Basket basket = getById(basket_id);
            Product product = productRepository.getById(product_id);
            basket.getCustomer().setMoney(basket.getCustomer().getMoney() - product.getPrice());
            Transaction transaction = session.beginTransaction();
            session.update(basket.getCustomer());
            transaction.commit();
        }
    }

    public void changeMoneyPlus(int basket_id, int product_id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Basket basket = getById(basket_id);
            Product product = productRepository.getById(product_id);
            basket.getCustomer().setMoney(basket.getCustomer().getMoney() + product.getPrice());
            Transaction transaction = session.beginTransaction();
            session.update(basket.getCustomer());
            transaction.commit();
        }
    }

    public void save(Basket basket) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(basket);
            transaction.commit();
        }
    }

    public void update(Basket basket) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(basket);
            transaction.commit();
        }
    }

    public void delete(Basket basket) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(basket);
            transaction.commit();
        }
    }
}
