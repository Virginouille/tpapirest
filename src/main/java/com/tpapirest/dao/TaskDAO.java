package com.tpapirest.dao;

import com.tpapirest.models.Task;
import com.tpapirest.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TaskDAO {

    /**Méthode read*/
    public List<Task> recupToutesTasks() {
        try(Session session = HibernateUtil.recupSessionFactory().openSession()) {
            return session.createQuery("from Task", Task.class).list();
        }
    }

    /**Méthode create*/
    public Task create(Task task) {
        Transaction tx = null;
        try(Session session = HibernateUtil.recupSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(task);
            tx.commit();
        } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                    throw e;
                }
        }
        return task;
    }

    /**Méthode update*/
    public Task update(Task task) {
        Transaction tx = null;
        try(Session session = HibernateUtil.recupSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(task);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
        return task;
    }
    /**Méthode delete*/
    public boolean delete(int id) {
        Transaction tx = null;
        try(Session session = HibernateUtil.recupSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.delete(task);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }


}
