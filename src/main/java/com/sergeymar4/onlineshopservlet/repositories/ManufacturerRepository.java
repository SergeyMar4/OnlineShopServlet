package com.sergeymar4.onlineshopservlet.repositories;

import com.sergeymar4.onlineshopservlet.models.Manufacturer;
import com.sergeymar4.onlineshopservlet.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManufacturerRepository {
    public List<Manufacturer> getAll() {
        List<Manufacturer> manufacturers = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            manufacturers = session.createQuery("from Manufacturer").list();
        }

        return manufacturers;
    }

    public Manufacturer getById(int id) {
        Manufacturer manufacturer = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            manufacturer = session.get(Manufacturer.class, id);
        }

        if (manufacturer == null) {
            System.out.println("Производителя с таким id нет");
        }

        return manufacturer;
    }

    public void save(Manufacturer manufacturer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(manufacturer);
            transaction.commit();
        }
    }

    public void update(Manufacturer manufacturer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(manufacturer);
            transaction.commit();
        }
    }

    public void delete(Manufacturer manufacturer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(manufacturer);
            transaction.commit();
        }
    }
}
