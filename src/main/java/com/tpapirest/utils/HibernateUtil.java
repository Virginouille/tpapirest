package com.tpapirest.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private final static SessionFactory sessionFactory = creaSessionFactory();

    /**Méthode pour générer une session factory*/
    private static SessionFactory creaSessionFactory() {
         try {
             return new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) {
             System.err.println("Problème d'initialisation de la session factory" + ex);
             throw new ExceptionInInitializerError(ex);
         }
    }

    /**Méthode qui récupère une Session factory*/
    public static SessionFactory recupSessionFactory() {
        return sessionFactory;
    }




}
