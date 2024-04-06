package com.sergeymar4.onlineshopservlet.repositories;

import com.sergeymar4.onlineshopservlet.models.Shop;
import com.sergeymar4.onlineshopservlet.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ShopRepository {
    public List<Shop> getAll() {
        List<Shop> shops = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            shops = session.createQuery("from Shop").list();
        }

        return shops;
    }

    public Shop getById(int id) {
        Shop shop = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            shop = session.get(Shop.class, id);
        }

        if (shop == null) {
            System.out.println("Магазина с таким id нет");
        }

        return shop;
    }

    public void save(Shop shop) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(shop);
            transaction.commit();
        }
    }

    public void update(Shop shop) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(shop);
            transaction.commit();
        }
    }

    public void delete(Shop shop) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(shop);
            transaction.commit();
        }
    }
}
